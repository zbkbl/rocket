#!/bin/bash

USG="Usage: $0  start|stop bossRcdWorker|geekRcdWorker|failoverRcdWorker|failoverRebuildCacheWorker [-conf confDir] [-logs logDir] [-mode lettuceConnMode] [-superd]"
if [ $# -lt 2 ] ; then
  echo $USG
  exit 2
fi

source /etc/profile

WORKER_NAME=$2
#The home directory for rcd process.
mypwd=$(cd `dirname "${BASH_SOURCE[0]}"` && pwd)/`basename "${BASH_SOURCE[0]}"`

mypwd=`dirname $mypwd`

ENGINE_HOME=$mypwd/..

# The java implementation to use. Required
#export JAVA_HOME=

#The maximum amount of heap to use, in MB. Default is 2048.
ENGINE_HEAPSIZE=10240
ENGINE_XMN=1024

# Where log files are stored.  $ENGINE_LOG_DIR/logs by default.
ENGINE_LOG_DIR=$ENGINE_HOME/logs
CONFIG_DIR=$ENGINE_HOME/conf
LETTUCE_MODE=0

ARR=($@)
NUMBER=$#
for ((i=1 ; i <= NUMBER ; i++))
do
  if [ "${ARR[$i]}" = "-conf" ] ; then
    CONFIG_DIR=${ARR[$((i+1))]}
  elif [ "${ARR[$i]}" = "-logs" ] ; then
    ENGINE_LOG_DIR=${ARR[$((i+1))]}
  elif [ "${ARR[$i]}" = "-heapsize" ] ; then
    ENGINE_HEAPSIZE=${ARR[$((i+1))]}
#  elif [ "${ARR[$i]}" = "-mode" ] ; then
#    LETTUCE_MODE=${ARR[$((i+1))]}
  elif [ "${ARR[$i]}" = "-superd" ] ; then
    SUPERD_FLAG=y
  fi
done

CLASSPATH=.:$JAVA_HOME/lib/tools.jar:$CONFIG_DIR
LIBPATH=$ENGINE_HOME/lib

for f in `find $LIBPATH -name '*.jar'|sort -r`
do
  CLASSPATH=$CLASSPATH:$f
done

# ******************************************************************
# ** Set java main class                                         **
# ** Set JVM heap PARAMS                                         **
# ** Set java runtime options                                    **
# ******************************************************************
case $WORKER_NAME in
bossRcdworker)
   CLASS_NAME=com.kanzhun.rps.engine.worker.BossRcdWorker
   OPT="-Xmx8192m -Xms8192m -XX:+UseG1GC -XX:-OmitStackTraceInFastThrow -Ddubbo.application.qos.enable=false -DconsumerHost=127.0.0.2 -DconsumerAppKey=rcd.feature.rank -Dtrace-name=boss-engine -XX:MaxGCPauseMillis=150 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   ;;
geekRcdworker)
   CLASS_NAME=com.kanzhun.rps.engine.worker.GeekRcdWorker
   OPT="-Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -XX:+UseG1GC -XX:-OmitStackTraceInFastThrow -Ddubbo.application.qos.enable=false -DconsumerHost=127.0.0.2 -DconsumerAppKey=rcd.feature.rank -Dtrace-name=geek-engine -XX:MaxGCPauseMillis=200 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   ;;
failoverRcdworker)
   CLASS_NAME=com.kanzhun.rps.engine.worker.FailoverRcdWorker
   ENGINE_HEAPSIZE=17408
   OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=60 -DconsumerHost=127.0.0.3 -DconsumerAppKey=rcd.feature.simplerank -XX:MaxGCPauseMillis=70 -Dtrace-name=simpleRank -DconsumerHost=127.0.0.3 -DconsumerAppKey=rcd.feature.simplerank -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintAdaptiveSizePolicy -XX:+PrintGCTimeStamps  -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   #ENGINE_HEAPSIZE=24576
   #OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:MetaspaceSize=17g -XX:MaxMetaspaceSize=17g -XX:NewSize=7g -XX:MaxNewSize=7g -Xss256K -XX:SurvivorRatio=48 -XX:ParallelGCThreads=8 -XX:MaxTenuringThreshold=15 -XX:TargetSurvivorRatio=80 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:+CMSParallelRemarkEnabled -XX:MaxGCPauseMillis=80 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:CMSMaxAbortablePrecleanTime=10000 -XX:+CMSClassUnloadingEnabled -XX:CMSScheduleRemarkEdenPenetration=1 -Dtrace-name=simpleRank -DconsumerHost=127.0.0.3 -DconsumerAppKey=rcd.feature.simplerank  -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   ;;
failoverRebuildCacheWorker)
   CLASS_NAME=com.kanzhun.rps.engine.worker.FailoverRebuildCacheWorker
   ENGINE_HEAPSIZE=17408
   OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=60 -DconsumerHost=127.0.0.3 -DconsumerAppKey=rcd.feature.simplerank -XX:MaxGCPauseMillis=70 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   #OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:MetaspaceSize=10g -XX:MaxMetaspaceSize=10g -XX:NewSize=3g -XX:MaxNewSize=3g -Xss256K -XX:SurvivorRatio=8 -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=15 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=90 -XX:MaxGCPauseMillis=150 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=1 -cp $CLASSPATH"
   ;;
FailoverIncbuildCacheWorker)
   CLASS_NAME=com.kanzhun.rps.engine.worker.FailoverIncbuildCacheWorker
   ENGINE_HEAPSIZE=5120
   OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=60 -DconsumerHost=127.0.0.3 -DconsumerAppKey=rcd.feature.simplerank -XX:MaxGCPauseMillis=70 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${ENGINE_LOG_DIR}/$WORKER_NAME.gc -cp $CLASSPATH"
   #OPT="-server -Xmx${ENGINE_HEAPSIZE}m -Xms${ENGINE_HEAPSIZE}m -Ddubbo.application.qos.enable=false -XX:MetaspaceSize=10g -XX:MaxMetaspaceSize=10g -XX:NewSize=3g -XX:MaxNewSize=3g -Xss256K -XX:SurvivorRatio=8 -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=15 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=90 -XX:MaxGCPauseMillis=150 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=1 -cp $CLASSPATH"
   ;;
*)
  echo -e " It does not support this worker:${WORKER_NAME} \n ${USG}"
  exit 2
esac



# ******************************************************************
# ** Set java runtime options                                      **
# ** start worker and mkdir logs dir .  **
# ******************************************************************

if [ ! -d $ENGINE_LOG_DIR ] ; then
  mkdir -p $ENGINE_LOG_DIR
fi

# ***************
# ** Run...    **
# ***************

PID=$ENGINE_LOG_DIR/$WORKER_NAME.pid
LOG=$ENGINE_LOG_DIR/rcd_info.log
EVALUATE_LOG=$ENGINE_LOG_DIR/${WORKER_NAME}_evaluate.log
ERR_LOG=$ENGINE_LOG_DIR/rcd_error.log
STATISTICS_LOG=$ENGINE_LOG_DIR/statistics.log

function start()
{
   ENV="-Ddubbo.application.qos.enable=false -Dstatistics.log=$STATISTICS_LOG -Drps.log=$LOG -Drps-error.log=$ERR_LOG -Devaluate.log=$EVALUATE_LOG -Dlog.appLogger=$WORKER_NAME -Dlog.errorLog=$WORKER_NAME-errorLog -Dlog.level=INFO -Dlettuce.read.mode=${LETTUCE_MODE} -Djava.util.Arrays.useLegacyMergeSort=true -Duser.dir=$ENGINE_HOME"
   if [ "$SUPERD_FLAG" = "y" ];then
     exec setuidgid work java $ENV $OPT $CLASS_NAME >> $ERR_LOG 2>&1 < /dev/null
   else
     exec java $ENV $OPT $CLASS_NAME >> $ERR_LOG 2>&1 < /dev/null &
   fi
   echo $! > $PID
}

function cache()
{
   ENV="-Ddubbo.application.qos.enable=false -Dstatistics.log=$STATISTICS_LOG -Drps.log=$LOG -Drps-error.log=$ERR_LOG -Devaluate.log=$EVALUATE_LOG -Dlog.appLogger=$WORKER_NAME -Dlog.errorLog=$WORKER_NAME-errorLog -Dlog.level=INFO -Dlettuce.read.mode=${LETTUCE_MODE} -Djava.util.Arrays.useLegacyMergeSort=true -Duser.dir=$ENGINE_HOME"
   exec java $ENV $OPT $CLASS_NAME >> $ERR_LOG 2>&1 < /dev/null
}

function stop()
{
  if [ -f $PID ]; then
    if kill -0 `cat $PID` > /dev/null 2>&1; then
      echo "stop $WORKER_NAME process ..."
      kill `cat $PID`
    fi
  else
      echo "It doesn't find $WORKER_NAME pid file:$PID"
  fi
}
if [ "$1" = "start" ] ; then
  start
elif [ "$1" = "stop" ] ; then
  stop
elif [ "$1" = "restart" ]; then
  stop && sleep 3 && start
elif [ "$1" = "cache" ] ; then
  cache
else
  echo "It doesn't support this command:$0"
fi

#!/bin/bash
export LANG="zh_CN.UTF-8"

SERVICE_NAME=bossrcd-rps-boss
SERVER_HOME=/service/$SERVICE_NAME
LOG_PATH=/data/logs/$SERVICE_NAME
CURRENT_MODEL_PATH=/data/app/rps/rps-boss/model/current
LAST_MODEL_PATH=/data/app/rps/rps-boss/model/last
WARM_PATH=/data/app/rps/warm

STARTUP_SH=rps-daemon.sh
STARTUP_CONF=online
if [ "${HOSTNAME:0:5}" = "ov-rd" ]
then
STARTUP_SH=rps-daemon-rd.sh
STARTUP_CONF=rd-01
elif [ "${HOSTNAME:0:5}" = "ov-qa" ]
then
STARTUP_SH=rps-daemon-qa.sh
STARTUP_CONF=qa-01
else
date
fi

#create this application current model path
if [ ! -d "$CURRENT_MODEL_PATH" ]; then
    mkdir -p "$CURRENT_MODEL_PATH"
    chown work.work /data/app/ -R
fi

#create this application last model path
if [ ! -d "$LAST_MODEL_PATH" ]; then
    mkdir -p "$LAST_MODEL_PATH"
    chown work.work /data/app/ -R
fi

if [ ! -d "$WARM_PATH" ]; then
    mkdir -p "$WARM_PATH"
    chown work.work /data/app/ -R
fi

rm -rf /data/app/rps/model/rps-boss/dpmodel/*

cmd="$SERVER_HOME/bin/$STARTUP_SH start bossRcdworker
-conf $SERVER_HOME/conf
-logs $LOG_PATH"

#截取分隔符号获取hostname的奇偶数
modeParam=$(hostname | awk -F "-" '{print $NF % 2}')

myid=`id -u`
if [ $myid -eq 0 ]; then
    # daemontools 会以root身份运行run脚本。用setuidgid切换到work账户。
    exec $cmd -mode $modeParam -superd 
else
    exec $cmd -mode $modeParam
fi
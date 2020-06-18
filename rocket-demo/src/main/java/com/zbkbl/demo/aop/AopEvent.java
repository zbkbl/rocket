package com.zbkbl.demo.aop;

import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.po.IDResp;
import com.zbkbl.demo.util.RankCacheContext;
import com.zbkbl.demo.util.ThreadLocalContext;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:21
 **/

@Slf4j
@Aspect
@Order(-1)
@Component
public class AopEvent {


    @Around("execution(* com.zbkbl.demo..*.*(..)) && @annotation(filterLogPoint))")
    public Object rcdFilterLog(ProceedingJoinPoint jp, FilterLogPoint filterLogPoint) throws Throwable {
        log.info("begin aspect rcdFilterLog ....");
        long start = System.currentTimeMillis();
        Object[] args = jp.getArgs();
        String pointName = filterLogPoint.name();
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        int idRespIndex = ArrayUtils.indexOf(parameterNames, "idResp");
        IDResp idResp = (IDResp) args[idRespIndex];
        Set beforeFilterSet = new HashSet(idResp.getIdResp());
        Object result = jp.proceed();
//        beforeFilterSet.removeAll(idResp.getIdResp());
        Iterator i = beforeFilterSet.iterator();
        Set<StudentVo> filterGeekSet = new HashSet<>();
        Set<UserVo> filterBossSet = new HashSet<>();
        while (i.hasNext()) {
            Object t = i.next();
            if (t instanceof StudentVo) {
                filterGeekSet.add((StudentVo) t);
            }
            if (t instanceof UserVo) {
                filterBossSet.add((UserVo) t);
            }
        }
        RankCacheContext context = ThreadLocalContext.getThreadLocal().get();
        context.setCurrentStepName("rcdFilterLog");
        if (pointName.equals("boss")) {
            log.info("boss log point,set:{}", filterBossSet);
            context.getRankLatencyCache().getFilterDetailLatency().put(context.getCurrentStepName(), System.currentTimeMillis() - start);
            log.info("context:{}", context);
        } else if (pointName.equals("geek")) {
            log.info("geek log point,set:{}", filterGeekSet);
            context.getRankLatencyCache().getFilterDetailLatency().put(context.getCurrentStepName(), System.currentTimeMillis() - start);
            log.info("context:{}", context);
        }
        return result;
    }
}

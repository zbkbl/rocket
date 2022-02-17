package com.zbkbl.demo.aop;

import com.google.common.collect.Sets;
import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.annotation.RepeatActivity;
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
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
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
//@Component
public class AopEvent {



//    @Around("execution(* com.zbkbl.demo..*.*(..)) && @annotation(filterLogPoint))")
    @Around("execution(* com.zbkbl.demo..AopTestService.test(..)) || execution(* com.zbkbl.demo..AopTestService.execute(..))")
    public Object rcdFilterLog(ProceedingJoinPoint jp) throws Throwable {
        log.info("begin aspect rcdFilterLog ....");
        long start = System.currentTimeMillis();
        Object[] args = jp.getArgs();
//        String pointName = filterLogPoint.name();
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        // TODO findAllMergedAnnotations 可找到父类或接口上的注解
        Set<Class<? extends Annotation>> annotationTypes = new HashSet<>();
        annotationTypes.add(FilterLogPoint.class);
        annotationTypes.add(Activity.class);
        Set<Annotation> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(methodSignature.getMethod(), annotationTypes);
        for(Annotation ac : allMergedAnnotations){
            if(ac.annotationType() == Activity.class){
                Activity at = (Activity) ac;
                log.info("AnnotatedElementUtils Activity::name {}", at.name());
            }
            if(ac.annotationType() == FilterLogPoint.class){
                FilterLogPoint fl = (FilterLogPoint) ac;
                log.info("AnnotatedElementUtils Activity::name {}", fl.name());
            }
        }
        Annotation[] annotations = methodSignature.getMethod().getAnnotations();
        for (Annotation annotation :  annotations){
            log.info("annotation:{}", annotation.annotationType());
            if (annotation.annotationType() == FilterLogPoint.class){
                FilterLogPoint fl = (FilterLogPoint) annotation;
                String name = fl.name();
                log.info("FilterLogPoint::name {}", name);
            }

            if (annotation.annotationType() == RepeatActivity.class){
                RepeatActivity ac = (RepeatActivity) annotation;
                for(Activity activity : ac.value()){
                    log.info("Activity::name {}", activity.name());
                }
            }
        }
        Object result = jp.proceed();
        return result;
    }
}

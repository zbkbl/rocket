package com.zbkbl.demo.aop;

import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.annotation.RepeatActivity;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: MethodAroundAdvice
 * @Description:
 * @author: LiuYang
 * @date: 2022/2/17 10:19 下午
 */
@Slf4j
//@Component("methodAroundAdvice")
public class MethodAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("MethodAroundAdvice::invoke!");
        Object proceed = invocation.proceed();
        Method method = invocation.getMethod();
        Annotation[] annotations = method.getAnnotations();
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

        Set<Class<? extends Annotation>> annotationTypes = new HashSet<>();
        annotationTypes.add(FilterLogPoint.class);
        annotationTypes.add(Activity.class);
        // TODO findAllMergedAnnotations 可找到父类或接口上的注解
        Set<Annotation> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(invocation.getMethod(), annotationTypes);
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
        return proceed;
    }
}
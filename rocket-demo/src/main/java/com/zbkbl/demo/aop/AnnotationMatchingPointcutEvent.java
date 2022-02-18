package com.zbkbl.demo.aop;

import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Title: AnnotationMatchingPointcutEvent
 * @Description:
 * @author: LiuYang
 * @date: 2022/2/17 10:17 下午
 */
@Slf4j
@Component
public class AnnotationMatchingPointcutEvent {

    /**
     * 对接口方法做切面的方案实现
     * @return
     */
    @Bean(name = "filterLogMethodAdvisor")
    public Advisor dataSourceAdvisor(@Qualifier("interfaceAopEvent") InterfaceAopEvent advice){
        Pointcut pointcut = new AnnotationMatchingPointcut(null, Activity.class, true);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

}
package com.zbkbl.demo.service.impl;

import com.zbkbl.demo.service.SpringEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.collect.Lists;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyang
 * @description
 * @date 2020/06/18 17:31
 **/
@Component
@Slf4j
public class SpringEventServiceImpl implements SpringEventService {

    private static ThreadLocal<List<ApplicationEvent>> eventCache = ThreadLocal.withInitial(Lists::newLinkedList);

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void publishEventSyn(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void publishEventAsync(ApplicationEvent event) {
        try {
            eventCache.get().add(event);
            this.tryPublish();
        } catch (Exception e) {
            log.error("publishEventAsyn error..", e);
        }
    }

    @Override
    public void publishEventAsync(List<ApplicationEvent> events) {
        try {
            events.forEach(event -> eventCache.get().add(event));
            this.tryPublish();
        } catch (Exception e) {
            log.error("publishEventAsyn error..", e);
        }
    }

    @Override
    public void tryPublish() {
        publishEventActual();
    }

    @Override
    public void removeCache() {
        eventCache.remove();
    }


    private Boolean publishEventActual() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            return Boolean.FALSE;
        } else {
            if (eventCache.get().isEmpty()) {
                return Boolean.TRUE;
            }
            eventCache.get().forEach(eventInCache -> applicationContext.publishEvent(eventInCache));
            eventCache.remove();
            return Boolean.TRUE;
        }
    }
}

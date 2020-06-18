package com.zbkbl.demo.service;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public interface SpringEventService {
    /**
     * 同步发送消息
     *
     * @param event
     */
    void publishEventSyn(ApplicationEvent event);

    /**
     * 异步发送消息(事务发送后)
     *
     * @param event
     */
    void publishEventAsync(ApplicationEvent event);

    /**
     * 异步发送消息(事务发送后)
     *
     * @param events
     */
    void publishEventAsync(List<ApplicationEvent> events);

    /**
     * 尝试发送消息
     */
    void tryPublish();

    /**
     * 删除缓存
     */
    void removeCache();
}

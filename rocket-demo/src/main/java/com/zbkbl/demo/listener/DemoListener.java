package com.zbkbl.demo.listener;

import com.zbkbl.demo.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liuyang
 * @description
 * @date 2020/06/18 17:27
 **/
@Slf4j
@Component
public class DemoListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == DemoEvent.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof DemoEvent) {
            DemoEvent d = (DemoEvent) event;
            System.out.println("publish demo event success! name:" + d.getName() + ", age:" + d.getAge());
        }
        System.out.println("publish success!");
    }
}

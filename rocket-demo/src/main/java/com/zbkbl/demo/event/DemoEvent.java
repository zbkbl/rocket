package com.zbkbl.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * @author liuyang
 * @description
 * @date 2020/06/18 17:26
 **/
@Getter
public class DemoEvent extends ApplicationEvent {

    private String name;
    private int age;

    public DemoEvent(String name , int age){
        super(LocalDateTime.now());
        this.name = name;
        this.age = age;
    }
}

package com.zbkbl.demo.service;

import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import org.springframework.stereotype.Component;

/**
 * @author liuyang
 * @description
 * @date 2020/06/05 15:03
 **/
@Component
public class AopTestServiceImpl extends AopTestService {
    @Override
    @FilterLogPoint(name = "geek")
    @Activity(name = "pinhaofan")
    @Activity(name = "tuanhaohuo")
    public void test() {
        System.out.println("aopTestServiceImpl");
    }
}

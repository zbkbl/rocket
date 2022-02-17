package com.zbkbl.demo.service;

/**
 * @author liuyang
 * @description
 * @date 2020/06/05 14:57
 **/
public abstract class AopTestService {

    /**
     * 模版方法模式
     * 抽象类调用this对象的test()方法,导致切点方法失效
     */
    public void execute() {
        test();

    }

    /**
     * 外部调用没问题,内部调用会导致其子类实现的方法上的切点失效
     */
    public abstract void test();
}

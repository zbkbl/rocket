package com.zbkbl.demo.service;

/**
 * @author liuyang
 * @description
 * @date 2020/06/05 14:57
 **/
public abstract class AopTestService {

    public void execute() {
        test();

    }


    protected abstract void test();
}

package com.zbkbl.demo.util;

/**
 * @author liuyang
 * @description 线程局部变量
 * @date 2020/05/21 10:20
 **/
public class ThreadLocalContext {
    private static final ThreadLocal<RankCacheContext> THREAD_LOCAL = new ThreadLocal<RankCacheContext>() {
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected RankCacheContext initialValue() {
            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return new RankCacheContext();
        }
    };

    public static ThreadLocal<RankCacheContext> getThreadLocal() {
        return THREAD_LOCAL;
    }
}

package test1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-02-15 10:21
 **/
public class ApiTestClient {

    public static void main(String[] args) {
       Container c = new Container();
       final CountDownLatch countDownLatch = new CountDownLatch(5);
       for(int i = 0;  i < 5 ; i++){
           final int j = i;
           Thread t = new Thread(()->{
               try {
                   countDownLatch.await();
                   c.setKV(j + "", j + "");
                   System.out.println(Thread.currentThread().getName() + " success set KV :"+ j);
               }catch (Exception e){
                   e.printStackTrace();
               }
           });
           t.setName("" + i + "->" + 1);
           t.start();
           countDownLatch.countDown();
       }
       final CountDownLatch c2 = new CountDownLatch(5);
       for(int j = 0 ; j < 5; j++){
            final int k = j;
           Thread t2 = new Thread(()->{
               try{
                   c2.await();
                   System.out.println(Thread.currentThread().getName() + " success get KV " + c.getValue(k + ""));
               }catch (Exception e){
                   e.printStackTrace();
               }
           });
           t2.setName("" + k + "->" + 2);
           t2.start();
           c2.countDown();
       }
    }


}

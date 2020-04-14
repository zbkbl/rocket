package abstractModel;

/**
 * @description: 华硕Mic
 * @author: Liuyang
 * @date: 2018-11-07 15:22
 **/
public class AsusMic implements Mic {
    @Override
    public void listen() {
        System.out.println("Inside AusMic::listen() method!");
    }
}

package abstractModel;

/**
 * @description: 惠普MIc
 * @author: Liuyang
 * @date: 2018-11-07 15:21
 **/
public class HpMic implements Mic {
    @Override
    public void listen() {
        System.out.println("Inside HpMic::listen() method!");
    }
}

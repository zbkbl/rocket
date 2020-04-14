package abstractModel;

/**
 * @description: 戴尔mic
 * @author: Liuyang
 * @date: 2018-11-07 15:20
 **/
public class DellMic implements Mic{
    @Override
    public void listen() {
        System.out.println("Inside DellMic::listen() method!");
    }
}

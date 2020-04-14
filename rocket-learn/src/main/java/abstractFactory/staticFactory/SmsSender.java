package abstractFactory.staticFactory;

/**
 * @description: 发送短信
 * @author: Liuyang
 * @date: 2018-11-07 11:16
 **/
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is smsSender!");
    }
}

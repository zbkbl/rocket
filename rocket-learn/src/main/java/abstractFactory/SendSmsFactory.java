package abstractFactory;


import abstractFactory.staticFactory.Sender;
import abstractFactory.staticFactory.SmsSender;

/**
 * @description: 短信工厂
 * @author: Liuyang
 * @date: 2018-11-07 11:25
 **/
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}

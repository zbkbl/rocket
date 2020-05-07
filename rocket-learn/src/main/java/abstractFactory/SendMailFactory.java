package abstractFactory;

import abstractFactory.staticFactory.MailSender;
import abstractFactory.staticFactory.Sender;

/**
 * @description: 邮件工厂
 * @author: Liuyang
 * @date: 2018-11-07 11:23
 **/
public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }

}

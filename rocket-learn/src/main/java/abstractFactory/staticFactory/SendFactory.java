package abstractFactory.staticFactory;


/**
 * @description: 静态工厂
 * @author: Liuyang
 * @date: 2018-11-07 11:18
 **/
public class SendFactory {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}

package abstractFactory.staticFactory;

/**
 * @description: 发送邮件
 * @author: Liuyang
 * @date: 2018-11-07 11:15
 **/
public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("this is mailSender!");
    }
}

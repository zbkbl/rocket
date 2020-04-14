package abstractModel;

/**
 * @description: 华硕鼠标
 * @author: Liuyang
 * @date: 2018-11-07 15:16
 **/
public class AsusMouse implements Mouse{
    @Override
    public void click() {
        System.out.println("Inside AsusMouse::click() method");
    }
}

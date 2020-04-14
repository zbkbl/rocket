package abstractModel;

/**
 * @description: 惠普鼠标
 * @author: Liuyang
 * @date: 2018-11-07 15:06
 **/
public class HpMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Inside HpMouse::click() method!");
    }
}

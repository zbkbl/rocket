package abstractModel;

/**
 * @description: 戴尔鼠标
 * @author: Liuyang
 * @date: 2018-11-07 15:04
 **/
public class DellMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Inside DellMouse::click() method!");
    }
}

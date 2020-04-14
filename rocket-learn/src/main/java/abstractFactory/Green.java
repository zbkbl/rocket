package abstractFactory;

/**
 * @description: 绿色
 * @author: Liuyang
 * @date: 2018-11-07 14:31
 **/
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method");
    }
}

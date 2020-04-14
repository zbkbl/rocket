package abstractFactory;

/**
 * @description: 红色
 * @author: Liuyang
 * @date: 2018-11-07 14:30
 **/
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method");
    }
}

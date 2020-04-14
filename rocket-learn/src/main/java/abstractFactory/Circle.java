package abstractFactory;

/**
 * @description: 圆形
 * @author: Liuyang
 * @date: 2018-11-07 14:29
 **/
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

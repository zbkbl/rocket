package abstractFactory;

/**
 * @description: 矩形
 * @author: Liuyang
 * @date: 2018-11-07 14:27
 **/
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

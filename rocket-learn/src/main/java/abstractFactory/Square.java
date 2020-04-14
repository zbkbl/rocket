package abstractFactory;

/**
 * @description: 正方形
 * @author: Liuyang
 * @date: 2018-11-07 14:29
 **/
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

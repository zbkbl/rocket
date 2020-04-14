package abstractFactory;

/**
 * @description: 抽象工厂
 * @author: Liuyang
 * @date: 2018-11-07 14:33
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}

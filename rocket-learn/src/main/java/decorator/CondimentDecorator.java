package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:39
 **/
public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDescription();

}

package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:45
 **/
public class Soy extends CondimentDecorator {
//    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.15;
    }
}

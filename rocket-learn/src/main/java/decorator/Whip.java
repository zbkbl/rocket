package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:47
 **/
public class Whip extends CondimentDecorator {
//    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;

    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}

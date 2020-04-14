package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:43
 **/
public class Mocha extends CondimentDecorator {
//    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
}

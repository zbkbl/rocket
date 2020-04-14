package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:40
 **/
public class Espresso extends Beverage {

    public Espresso(){
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

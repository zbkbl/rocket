package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:42
 **/
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}

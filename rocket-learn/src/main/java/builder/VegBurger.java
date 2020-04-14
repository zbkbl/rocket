package builder;

/**
 * @description: 素食汉堡
 * @author: Liuyang
 * @date: 2018-11-07 15:59
 **/
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

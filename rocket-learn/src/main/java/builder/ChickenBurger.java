package builder;

/**
 * @description: 鸡肉汉堡
 * @author: Liuyang
 * @date: 2018-11-07 16:00
 **/
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}

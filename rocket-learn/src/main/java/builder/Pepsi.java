package builder;

/**
 * @description:
 * @author: Liuyang
 * @date: 2018-11-07 16:01
 **/
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}

package builder;

/**
 * @description: 可乐
 * @author: Liuyang
 * @date: 2018-11-07 16:01
 **/
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}

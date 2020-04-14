package builder;

/**
 * @description: 冷饮
 * @author: Liuyang
 * @date: 2018-11-07 15:58
 **/
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

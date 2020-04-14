package builder;

/**
 * @description: 汉堡
 * @author: Liuyang
 * @date: 2018-11-07 15:56
 **/
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}

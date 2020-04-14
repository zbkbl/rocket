package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:51
 **/
public class DarkRoast extends Beverage {

    public DarkRoast(){
        this.description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}

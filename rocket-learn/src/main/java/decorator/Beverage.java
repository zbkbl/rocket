package decorator;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 10:37
 **/
public abstract class Beverage {

    public String description  = "Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}

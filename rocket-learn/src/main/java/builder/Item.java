package builder;

/**
 * @description: 食物条目
 * @author: Liuyang
 * @date: 2018-11-07 15:53
 **/
public interface Item {
    String name();

    Packing packing();

    float price();
}

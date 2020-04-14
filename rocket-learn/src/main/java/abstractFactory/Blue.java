package abstractFactory;

/**
 * @description: 蓝色
 * @author: Liuyang
 * @date: 2018-11-07 14:32
 **/
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method");
    }
}

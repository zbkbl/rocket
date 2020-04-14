package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 11:53
 **/
public class Light {
    String str;

    public Light(String str) {
        this.str = str;
    }

    public void on() {
        System.out.println(str + " light is on!");
    }

    public void off() {
        System.out.println(str + " light is off!");
    }
}

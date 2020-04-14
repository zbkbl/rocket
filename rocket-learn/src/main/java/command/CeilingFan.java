package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 17:17
 **/
public class CeilingFan {

    String str;

    public CeilingFan(String str) {
        this.str = str;
    }

    public void on() {
        System.out.println(str + " ceilingFan is on!");
    }

    public void off() {
        System.out.println(str + " ceilingFan is off!");
    }
}

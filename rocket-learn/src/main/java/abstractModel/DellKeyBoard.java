package abstractModel;

/**
 * @description: 戴尔键盘
 * @author: Liuyang
 * @date: 2018-11-07 15:07
 **/
public class DellKeyBoard implements KeyBoard {
    @Override
    public void sayHi() {
        System.out.println("Inside DellKeyBoard::sayHi() method!");
    }
}

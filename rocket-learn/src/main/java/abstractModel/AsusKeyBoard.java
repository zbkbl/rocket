package abstractModel;

/**
 * @description: 华硕键盘
 * @author: Liuyang
 * @date: 2018-11-07 15:17
 **/
public class AsusKeyBoard implements KeyBoard {
    @Override
    public void sayHi() {
        System.out.println("Inside AsusKeyBoard::sayHi() method!");
    }
}

package abstractModel;

/**
 * @description: 惠普键盘
 * @author: Liuyang
 * @date: 2018-11-07 15:08
 **/
public class HpKeyBoard implements KeyBoard {
    @Override
    public void sayHi() {
        System.out.println("Inside HpKeyBoard::sayHi() method!");
    }
}

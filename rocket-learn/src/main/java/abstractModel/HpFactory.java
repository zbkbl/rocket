package abstractModel;

/**
 * @description: 惠普工厂
 * @author: Liuyang
 * @date: 2018-11-07 15:11
 **/
public class HpFactory extends PcFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Mic createMic() {
        return new HpMic();
    }

    @Override
    public KeyBoard createBoard() {
        return new HpKeyBoard();
    }
}

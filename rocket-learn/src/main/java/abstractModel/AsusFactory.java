package abstractModel;

/**
 * @description: 华硕工厂
 * @author: Liuyang
 * @date: 2018-11-07 15:16
 **/
public class AsusFactory extends PcFactory {
    @Override
    public Mouse createMouse() {
        return new AsusMouse();
    }

    @Override
    public Mic createMic() {
        return new AsusMic();
    }

    @Override
    public KeyBoard createBoard() {
        return new AsusKeyBoard();
    }
}

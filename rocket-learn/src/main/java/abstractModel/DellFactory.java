package abstractModel;

/**
 * @description: 戴尔工厂
 * @author: Liuyang
 * @date: 2018-11-07 15:12
 **/
public class DellFactory extends PcFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Mic createMic() {
        return new DellMic();
    }

    @Override
    public KeyBoard createBoard() {
        return new DellKeyBoard();
    }
}

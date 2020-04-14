package abstractModel;

/**
 * @description: Pc厂商
 * @author: Liuyang
 * @date: 2018-11-07 15:09
 **/
public abstract class PcFactory {

    public abstract Mouse createMouse();

    public abstract KeyBoard createBoard();

    public abstract Mic createMic();
}

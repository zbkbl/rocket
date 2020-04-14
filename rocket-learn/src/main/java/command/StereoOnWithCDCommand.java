package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 17:12
 **/
public class StereoOnWithCDCommand implements Command {

    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
        stereo.setCd();
        stereo.setVolume(11);
    }
}

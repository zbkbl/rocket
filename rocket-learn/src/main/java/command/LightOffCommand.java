package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 17:07
 **/
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}

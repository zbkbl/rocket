package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 11:52
 **/
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

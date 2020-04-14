package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 11:57
 **/
public class GarageDoorOpenCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.open();
    }
}

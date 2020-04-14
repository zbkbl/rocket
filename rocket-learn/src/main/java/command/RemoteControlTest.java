package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 11:56
 **/
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Light light = new Light("living room");
        GarageDoor garageDoor = new GarageDoor();

        LightOnCommand lightOn = new LightOnCommand(light);
        GarageDoorOpenCommand doorOpen = new GarageDoorOpenCommand(garageDoor);

        remote.setCommand(lightOn);

        remote.buttonWasPressed();
        remote.setCommand(doorOpen);
        remote.buttonWasPressed();
    }
}

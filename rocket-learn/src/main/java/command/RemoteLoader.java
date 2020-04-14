package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 17:14
 **/
public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor();
        Stereo stereo = new Stereo("Living Room");

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);

        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        NoCommand noCommand = new NoCommand();

        remoteControl.setCommand(0,livingRoomLightOn,lightOffCommand);
        remoteControl.setCommand(1,kitchenLightOn,kitchenLightOff);
        remoteControl.setCommand(2,garageDoorOpenCommand,noCommand);
        remoteControl.setCommand(3,stereoOnWithCDCommand,noCommand);

        System.out.println(remoteControl);
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);

        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);

        remoteControl.onButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);

    }
}

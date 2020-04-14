package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 11:54
 **/
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl(){

    }

    public void setCommand(Command command){
        this.slot = command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}

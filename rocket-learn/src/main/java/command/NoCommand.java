package command;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-25 16:59
 **/
public class NoCommand  implements Command {

    @Override
    public void execute() {
        System.out.println(" this command is none");
    }

}

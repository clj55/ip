package psyduck.command;
import static psyduck.Psyduck.taskList;
import psyduck.task.Task;

public class ListCommand extends Command {
    public CommandResult execute() {
        int num = 1;
        for (Task s : taskList) {
            System.out.print(num + ".");
            s.printTask();
            System.out.println();
            num += 1;
        }
        if (num == 1) { //nothing in tasklist
            System.out.println("No Psyduck Tasks");
        }
        return null;
    }
}

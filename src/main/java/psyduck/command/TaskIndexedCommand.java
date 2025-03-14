package psyduck.command;

import static psyduck.Psyduck.count;
import static psyduck.Psyduck.taskList;

import psyduck.exception.TaskIndexUndefinedException;
import psyduck.task.Task;

public abstract class TaskIndexedCommand extends Command {
    private int index;
    protected Task task;
    private boolean isSuccessParse = true; //parseTaskIndexedInstruction executes with no error

    public TaskIndexedCommand(String userInput) {
        super();
        try {
            this.index = parseTaskIndexedInstruction(userInput);
            this.task = taskList.get(index);
        } catch (TaskIndexUndefinedException e) {
            this.setSuccessParse(false);
        }
    }

    private int parseTaskIndexedInstruction(String userInput) throws TaskIndexUndefinedException {
        try {
            String[] details = userInput.split(" ", 2);
            int iNum = Integer.parseInt(details[1]);
            if ((iNum > count) || iNum <= 0) {
                System.out.println("Psyduck: quacker not in list");
                throw new TaskIndexUndefinedException("Task Number not within number of tasks in TaskList");
            }
            iNum -= 1; // convert task number to index in tasklist
            return iNum;
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
            throw new TaskIndexUndefinedException("User input Non-number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: what Task number");
            throw new TaskIndexUndefinedException("User did not input Task Name");
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isSuccessParse() {
        return isSuccessParse;
    }

    public void setSuccessParse(boolean successParse) {
        isSuccessParse = successParse;
    }

}

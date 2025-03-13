package psyduck.command;
import psyduck.task.Task;

/**
 * Contains Task Number and the converted text of the task to store in the task file
 */
public class CommandResult {
    public int taskNum;
    public String toStore;
    public boolean isDelete;

    /**
     * Creates Command Result from Command
     * @param taskNum Task Number in TaskList
     * @param task The new/modified/deleted task
     * @param isDelete Indicates if the Command Result was from the Delete Command
     */
    public CommandResult(int taskNum, Task task, boolean isDelete) {
        this.isDelete = isDelete;
        this.taskNum = taskNum;
        this.toStore = task.toString();
    }

    /**
     * Creates Command Result from Commands that only modify and do not delete tasks
     * @param taskNum Task Number in TaskList
     * @param task The new/modified task
     */
    public CommandResult(int taskNum, Task task) {
        this.isDelete = false;
        this.taskNum = taskNum;
        this.toStore = task.toString();
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public String getToStore() {
        return toStore;
    }

    public void setToStore(String toStore) {
        this.toStore = toStore;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

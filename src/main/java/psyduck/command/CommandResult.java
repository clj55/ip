package psyduck.command;
import psyduck.task.Task;

public class CommandResult {
    public int taskNum;
    public String toStore;
    public boolean isDelete;

    public CommandResult(int taskNum, Task task, boolean isDelete) {
        this.isDelete = isDelete;
        this.taskNum = taskNum;
        this.toStore = task.toString();
    }

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

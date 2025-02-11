package psyduck.task;

public class Task {
    private String taskName;
    private char taskType;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskType = 'T';
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public char getTaskType() {
        return taskType;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    public boolean checkDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void printTask() {
        System.out.print("[" + this.taskType + "][");
        if (this.checkDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.print("] " + this.taskName);
    }
}


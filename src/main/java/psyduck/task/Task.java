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

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.taskType = 'T';
        this.isDone = isDone;
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
        System.out.print(this);
    }

    public String toString() {
        String output = "[" + this.taskType + "][";
        if (this.checkDone()) {
            output += "X";
        } else {
            output += " ";
        }
        output += "] " + this.taskName;
        return output;
    }

    public String toFileString() {
        return this.getTaskType() + "/" + (this.checkDone() ? 1 : 0) + "/" + this.getTaskName();
    }
}


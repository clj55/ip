public class Task {
    private String taskName;
    private char taskType;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskType = 'T';
        this.done = false;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}


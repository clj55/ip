package psyduck.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.setTaskType('E');
        this.start = start;
        this.end = end;
    }

    public Event(String taskName, boolean isDone, String start, String end) {
        super(taskName, isDone);
        this.setTaskType('E');
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String toString() {
        return super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    public String toFileString() {
        return this.getTaskType() + "/"
                + (this.checkDone() ? 1 : 0) + "/" + this.getTaskName()
                + "/" + this.getStart() + "/" + this.getEnd();
    }
}

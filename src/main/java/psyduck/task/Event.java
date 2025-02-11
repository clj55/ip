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

    public void printTask() {
        super.printTask();
        System.out.print(" (from: " + this.start + " to: " + this.end + ")");
    }
}

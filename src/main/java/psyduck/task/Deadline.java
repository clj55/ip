package psyduck.task;

public class Deadline extends Task {
    private String datetime;

    public Deadline(String taskName, String datetime) {
        super(taskName);
        this.setTaskType('D');
        this.datetime = datetime;
    }

    public Deadline(String taskName, boolean isDone, String datetime) {
        super(taskName, isDone);
        this.setTaskType('D');
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void printTask() {
        super.printTask();
        System.out.print(" (by: " + this.datetime + ")");
    }
}

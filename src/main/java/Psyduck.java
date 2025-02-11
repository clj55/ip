import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Psyduck {
    private static int count = 0;

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        printIntro();
        String userInput = getUserInput(scanObj);
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks(taskList);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput, taskList);
            } else if (userInput.startsWith("mark")) {
                markTask(userInput, taskList);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput, taskList);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput, taskList);
            } else if (userInput.startsWith("todo")) {
                addTodo(userInput, taskList);
            } else {
                System.out.println("nani??");
            }
            userInput = getUserInput(scanObj);
        }
        System.out.println("PSYYYYDUCKKKK");
        printDashes();
    }

    private static String getUserInput(Scanner scanObj) {
        printDashes();
        String userInput;
        System.out.print("Me: ");
        userInput = scanObj.nextLine();
        userInput = userInput.trim();
        printDashes();
        return userInput;
    }

    private static void addTodo(String userInput, List<Task> taskList) {
        String details = userInput.substring(4);
        Task task = new Task(details);
        taskList.add(task);
        count++;
        printAddTaskStatement(task);
    }

    private static void addEvent(String userInput, List<Task> taskList) {
        String[] details = userInput.split(" ", 2);
        String[] splitted = details[1].split("/");
        Event newEvent = new Event(splitted[0].trim(), splitted[1], splitted[2]);
        taskList.add(newEvent);
        count++;
        printAddTaskStatement(newEvent);
    }

    private static void addDeadline(String userInput, List<Task> taskList) {
        String[] details = userInput.split(" ", 2);
        String[] splitted = details[1].split("/");
        Deadline newDeadline = new Deadline(splitted[0].trim(), splitted[1]);
        taskList.add(newDeadline);
        count++;
        printAddTaskStatement(newDeadline);
    }

    private static void printIntro() {
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
    }

    public static void printDashes() {
        System.out.println("--------------------------------------------");
    }

    private static void printAddTaskStatement(Task task) {
        System.out.print("Psyduck added: ");
        task.printTask();
        System.out.println();
        System.out.println("You have " + count + " psyduck tasks");
    }

    private static void markTask(String userInput, List<Task> taskList) {
        try {
            String[] details = userInput.split(" ", 2);
            int iNum = Integer.parseInt(details[1]);
            if ((iNum > taskList.size()) || iNum <= 0) {
                System.out.println("quacker not in list");
            } else {
                System.out.println("Psyduck is impressed");
                iNum -= 1; // convert task number to index in tasklist
                Task task = taskList.get(iNum);
                task.setDone(true);
                task.printTask();
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
        }
    }

    private static void unmarkTask(String userInput, List<Task> taskList) {
        try {
            String[] details = userInput.split(" ", 2);
            int iNum = Integer.parseInt(details[1]);
            if ((iNum > taskList.size()) || (iNum <= 0)) {
                System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBER IN RANGE");
            } else {
                System.out.println("Psyduck is NOT impressed");
                iNum -= 1;
                Task task = taskList.get(iNum);
                task.setDone(false);
                task.printTask();
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
        }
    }

    public static void listTasks(List<Task> taskList) {
        int num = 1;
        for (Task s : taskList) {
            System.out.print(num + ".");
            s.printTask();
            System.out.println();
            num += 1;
        }
    }
}





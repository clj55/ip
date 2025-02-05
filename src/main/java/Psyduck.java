import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Psyduck {
    private static int count = 0;

    public static void printDashes() {
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
        printDashes();
        System.out.print("Me: ");
        String userInput = scanObj.nextLine();
        printDashes();
        userInput = userInput.trim();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks(taskList);
            } else if (userInput.startsWith("unmark")) {
                String sNum = userInput.substring(7);
                unmarkTask(sNum, taskList);
            } else if (userInput.startsWith("mark")) {
                String sNum = userInput.substring(5);
                markTask(sNum, taskList);
            } else if (userInput.startsWith("deadline")) {
                String s = userInput.substring(8);
                String[] splitted = s.split("/");
                Deadline newDeadline = new Deadline(splitted[0].trim(), splitted[1]);
                taskList.add(newDeadline);
                count++;
                printAddTaskStatement(newDeadline);
            } else if (userInput.startsWith("event")) {
                String s = userInput.substring(5);
                String[] splitted = s.split("/");
                Event newEvent = new Event(splitted[0].trim(), splitted[1], splitted[2]);
                taskList.add(newEvent);
                count++;
                printAddTaskStatement(newEvent);
            } else { // add task
                Task task = new Task(userInput);
                taskList.add(task);
                count++;
                printAddTaskStatement(task);
            }
            printDashes();
            System.out.print("Me: ");
            userInput = scanObj.nextLine();
            userInput = userInput.trim();
            printDashes();
        }
        System.out.println("PSYYYYDUCKKKK");
        printDashes();

    }

    private static void printAddTaskStatement(Task task) {
        System.out.print("Psyduck added: ");
        task.printTask();
        System.out.println();
        System.out.println("You have " + count + " psyduck tasks");
    }

    private static void markTask(String sNum, List<Task> taskList) {
        try {
            int iNum = Integer.parseInt(sNum);
            if ((iNum > taskList.size()) || iNum <= 0) {
                System.out.println("quacker not in list");
            } else {
                System.out.println("Psyduck is impressed");
                iNum -= 1; // adjust to start with zero index
                Task task = taskList.get(iNum);
                task.setDone(true);
                task.printTask();
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
        }
    }

    private static void unmarkTask(String sNum, List<Task> taskList) {
        try {
            int iNum = Integer.parseInt(sNum);
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





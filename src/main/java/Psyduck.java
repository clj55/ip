import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Psyduck {
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
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks(taskList);
            } else if (userInput.startsWith("unmark")) {
                String sNum = userInput.substring(7);
                unmarkTask(sNum, taskList);
            } else if (userInput.startsWith("mark")) {
                String sNum = userInput.substring(5);
                markTask(sNum, taskList);
//            } else if (userInput.startsWith("deadline")) {

            } else { // add task
                Task task = new Task(userInput);
                taskList.add(task);
                System.out.print("Psyduck added: ");
                System.out.println(userInput);
            }
            printDashes();
            System.out.print("Me: ");
            userInput = scanObj.nextLine();
            printDashes();
        }
        System.out.println("PSYYYYDUCKKKK");
        printDashes();

    }

    private static void markTask(String sNum, List<Task> taskList) {
        try {
            int iNum = Integer.parseInt(sNum);
            if ((iNum > taskList.size()) || iNum <= 0) {
                System.out.println("quacker not in list");
            } else {
                System.out.println("Psyduck is impressed");
                iNum -= 1; // adjust to start with zero index
                taskList.get(iNum).setDone(true);
                System.out.println("[X] " + taskList.get(iNum).getTaskName());
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
                taskList.get(iNum).setDone(false);
                System.out.println("[ ] " + taskList.get(iNum).getTaskName());
            }
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
        }
    }

    public static void listTasks(List<Task> taskList){
        int num = 1;
        for (Task s : taskList) {
            System.out.print(num + ".[");
            if (s.isDone()) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            System.out.println("] " + s.getTaskName());
            num += 1;
        }
    }
}





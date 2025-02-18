package psyduck.ui;

import java.util.ArrayList;
import java.util.Scanner;

import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;

public class Psyduck {
    private static int count = 0;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner scanObj = new Scanner(System.in);

    public static void main(String[] args) {
        printIntro();
        String userInput = getUserInput();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput);
            } else if (userInput.startsWith("todo")) {
                addTodo(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else {
                System.out.println("nani??");
            }
            userInput = getUserInput();
        }
        System.out.println("PSYYYYDUCKKKK");
        printDashes();
    }



    private static String getUserInput() {
        printDashes();
        String userInput;
        System.out.print("Me: ");
        userInput = scanObj.nextLine();
        userInput = userInput.trim();
        printDashes();
        return userInput;
    }

    private static void addTodo(String userInput) {
        String details = userInput.substring(4);
        if (details.isEmpty()) {
            System.out.println("Psyduck dont know what you want to do");
            return;
        }
        Task task = new Task(details);
        taskList.add(task);
        count++;
        printAddTaskStatement(task);
    }

    private static void addEvent(String userInput) {
        try {
            String[] splitted = parseTask(userInput);
            Event newEvent = new Event(splitted[0], splitted[1], splitted[2]);
            taskList.add(newEvent);
            count++;
            printAddTaskStatement(newEvent);
        } catch (ArrayIndexOutOfBoundsException e) {
            //if user did not put start &/or end time
            System.out.println("Psyduck: Nanji kara nanji made desuka (What time start what time end)?");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Event");
        }
    }

    private static void addDeadline(String userInput) {
        try {
            String[] splitted = parseTask(userInput);
            Deadline newDeadline = new Deadline(splitted[0], splitted[1]);
            taskList.add(newDeadline);
            count++;
            printAddTaskStatement(newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: Shimekiri ha itsu desuka? (When is the deadline?)");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Deadline");
        }

    }

    private static String[] parseTask(String userInput) throws TaskUndefinedException, ArrayIndexOutOfBoundsException {
        if (!userInput.contains(" ")) {
            throw new TaskUndefinedException();
        }
        String[] details = userInput.split(" ", 2);
        String[] splitted = details[1].split("/");
        if (splitted.length == 0 || splitted[0].isEmpty()) {
            throw new TaskUndefinedException();
        }
        splitted[0] = splitted[0].trim();
        return splitted;
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

    private static void markTask(String userInput) {
        try {
            Task task = parseTaskIndexedInstruction(userInput);
            System.out.println("Psyduck is impressed");
            task.setDone(true);
            task.printTask();
            System.out.println();
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            Task task = parseTaskIndexedInstruction(userInput);
            System.out.println("Psyduck is NOT impressed");
            task.setDone(false);
            task.printTask();
            System.out.println();
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    private static void deleteTask (String userInput) {
        try {
            Task task = parseTaskIndexedInstruction(userInput);
            System.out.println("Psyduck deleted task: ");
            task.printTask();
            System.out.println();
            taskList.remove(task);
            count--;
            System.out.println("Now you have " + count + " psyduck tasks");
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    // parses mark/unmark instruction and returns corresponding task to mark/unmark
    private static Task parseTaskIndexedInstruction(String userInput) throws TaskIndexUndefinedException {
        try {
            String[] details = userInput.split(" ", 2);
            int iNum = Integer.parseInt(details[1]);
            if ((iNum > count) || iNum <= 0) {
                System.out.println("Psyduck: quacker not in list");
                throw new TaskIndexUndefinedException();
            }
            iNum -= 1; // convert task number to index in tasklist
            return taskList.get(iNum);
        } catch (NumberFormatException e) {
            System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
            throw new TaskIndexUndefinedException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: what Task number");
            throw new TaskIndexUndefinedException();
        }
    }

    public static void listTasks() {
        int num = 1;
        for (Task s : taskList) {
            System.out.print(num + ".");
            s.printTask();
            System.out.println();
            num += 1;
        }
    }
}





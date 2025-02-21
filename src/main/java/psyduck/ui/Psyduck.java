package psyduck.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;


public class Psyduck {
    private static final String TASK_LIST_FILEPATH = "./PsyduckData/tasks.txt";
    private static final Path path = Paths.get(TASK_LIST_FILEPATH);
    private static int count = 0;
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final Scanner userInputScanner = new Scanner(System.in);
    private static List<String> fileLines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File f = new File(TASK_LIST_FILEPATH);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("could not create tasks.txt file");// create tasks file if it does not exist
        }

        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String fileLine = fileScanner.nextLine();
            if (fileLine.isBlank()) {
                continue;
            }
            String[] taskDetails = fileLine.split("/");
            boolean taskIsDone = taskDetails[1].equals("1");
            switch (taskDetails[0]) {
            case "T" -> addtoTaskList(new Task(taskDetails[2], taskIsDone));
            case "D" -> addtoTaskList(new Deadline(taskDetails[2], taskIsDone, taskDetails[3]));
            case "E" -> addtoTaskList(new Event(taskDetails[2], taskIsDone, taskDetails[3], taskDetails[4]));
            }
        }
        printIntro();
        String userInput = getUserInput();
        while (!userInput.equals("bye")) {
            //update fileLines
            fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);

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

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static String getUserInput() {
        printDashes();
        String userInput;
        System.out.print("Me: ");
        userInput = userInputScanner.nextLine();
        userInput = userInput.trim();
        printDashes();
        return userInput;
    }

    private static void rewriteLine(int lineIndex, String data) throws IOException {
        fileLines.set(lineIndex, data);
        Files.write(path, fileLines, StandardCharsets.UTF_8);
    }

    private static void addTodo(String userInput) throws IOException {
        String details = userInput.substring(4);
        if (details.isEmpty()) {
            System.out.println("Psyduck dont know what you want to do");
            return;
        }
        Task task = new Task(details.trim());
        addtoTaskList(task);
        appendToFile(TASK_LIST_FILEPATH, task.getTaskType() + "/" +
                +(task.checkDone() ? 1 : 0) + "/" + task.getTaskName() + "\n");
    }

    private static void addtoTaskList(Task task) throws IOException {
        taskList.add(task);
        count++;
        printAddTaskStatement(task);
    }

    private static void addEvent(String userInput) throws IOException {
        try {
            String[] splitted = parseTask(userInput);
            Event newEvent = new Event(splitted[0], splitted[1], splitted[2]);
            addtoTaskList(newEvent);
            appendToFile(TASK_LIST_FILEPATH, newEvent.getTaskType() + "/"
                    + (newEvent.checkDone() ? 1 : 0) + "/" + newEvent.getTaskName()
                    + "/" + newEvent.getStart() + "/" + newEvent.getEnd() + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            //if user did not put start &/or end time
            System.out.println("Psyduck: Nanji kara nanji made desuka (What time start what time end)?");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Event");
        }
    }

    private static void addDeadline(String userInput) throws IOException {
        try {
            String[] splitted = parseTask(userInput);
            Deadline newDeadline = new Deadline(splitted[0], splitted[1]);
            addtoTaskList(newDeadline);
            appendToFile(TASK_LIST_FILEPATH,newDeadline.getTaskType() + "/" +
                    (newDeadline.checkDone() ? 1 : 0) + "/" + newDeadline.getTaskName()
                    + "/" + newDeadline.getDatetime() + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: Shimekiri ha itsu desuka? (When is the deadline?)");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Deadline");
        }

    }

    //TODO: check if this function can handle event/todo/deadline
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

    private static void markTask(String userInput) throws IOException {
        try {
            int iNum = parseTaskIndexedInstruction(userInput);
            Task task = taskList.get(iNum);
            System.out.println("Psyduck is impressed");
            task.setDone(true);
            String editedLine = fileLines.get(iNum).replaceFirst("0", "1");
            rewriteLine(iNum, editedLine);
            task.printTask();
            System.out.println();
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    private static void unmarkTask(String userInput) throws IOException {
        try {
            int iNum = parseTaskIndexedInstruction(userInput);
            Task task = taskList.get(iNum);
            System.out.println("Psyduck is NOT impressed");
            task.setDone(false);
            String editedLine = fileLines.get(iNum).replaceFirst("1", "0");
            rewriteLine(iNum, editedLine);
            task.printTask();
            System.out.println();
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    private static void deleteTask (String userInput) throws IOException {
        try {
            int iNum = parseTaskIndexedInstruction(userInput);
            Task task = taskList.get(iNum);
            System.out.println("Psyduck deleted task: ");
            task.printTask();
            System.out.println();
            taskList.remove(task);
            fileLines.remove(iNum);
            Files.write(path, fileLines, StandardCharsets.UTF_8);
            count--;
            System.out.println("Now you have " + count + " psyduck tasks");
        } catch (TaskIndexUndefinedException e) {
            //Exception handled in parseMarker
        }
    }

    // parses mark/unmark instruction and returns corresponding task index
    private static int parseTaskIndexedInstruction(String userInput) throws TaskIndexUndefinedException {
        try {
            String[] details = userInput.split(" ", 2);
            int iNum = Integer.parseInt(details[1]);
            if ((iNum > count) || iNum <= 0) {
                System.out.println("Psyduck: quacker not in list");
                throw new TaskIndexUndefinedException();
            }
            iNum -= 1; // convert task number to index in tasklist
            return iNum;
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





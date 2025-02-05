import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Psyduck {
    public static void printDashes() {
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        List<String> storage = new ArrayList<>();
        List<Boolean> isDone = new ArrayList<>();
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
        printDashes();
        System.out.print("Me: ");
        String userInput = scanObj.nextLine();
        printDashes();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                int num = 1;
                for (String s : storage) {
                    System.out.print(num + ".[");
                    if (isDone.get(num - 1)) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.println("] " + s);
                    num += 1;
                }
            } else if (userInput.startsWith("unmark")) {

                String sNum = userInput.substring(7);
                // System.out.println(sNum);
                try {
                    int iNum = Integer.parseInt(sNum);
                    if ((iNum > isDone.size()) || (iNum <= 0)) {
                        System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBER IN RANGE");
                    } else {
                        System.out.println("Psyduck is NOT impressed");
                        iNum -= 1; // adjust to start with zero index
                        isDone.set(iNum, false);
                        System.out.println("[ ] " + storage.get(iNum));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
                }

            } else if (userInput.startsWith("mark")) {

                String sNum = userInput.substring(5);
                // System.out.println(sNum);
                try {
                    int iNum = Integer.parseInt(sNum);
                    if ((iNum > isDone.size()) || iNum <= 0) {
                        System.out.println("quacker not in list");
                    } else {
                        System.out.println("Psyduck is impressed");
                        iNum -= 1; // adjust to start with zero index
                        isDone.set(iNum, true);
                        System.out.println("[X] " + storage.get(iNum));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("PSYDUCK ANGRY: PSYDUCK WANT NUMBERS");
                }
            } else {
                storage.add(userInput);
                isDone.add(false);
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
}

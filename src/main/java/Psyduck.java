import java.util.Scanner;
public class Psyduck {
    public static void printDashes () {
        System.out.println("--------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
        printDashes();
        System.out.print("Me: ");
        String userInput = scanObj.nextLine();
        printDashes();
        while (!userInput.equals("bye")) {
            System.out.print("Psyduck: ");
            System.out.println(userInput);
            printDashes();
            System.out.print("Me: ");
            userInput = scanObj.nextLine();
            printDashes();
        }
        System.out.println("PSYYYYDUCKKKK");
        printDashes();
        
    }
}

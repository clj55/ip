import java.util.Scanner;
public class Psyduck {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
        System.out.println("--------------------------------------------");
        String userInput = scanObj.nextLine();
        System.out.println("--------------------------------------------");
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            System.out.println("--------------------------------------------");
            userInput = scanObj.nextLine();
            System.out.println("--------------------------------------------");
        }
        System.out.println("PSYYYYDUCKKKK");
        System.out.println("--------------------------------------------");
        
    }
}

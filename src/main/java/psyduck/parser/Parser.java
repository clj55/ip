package psyduck.parser;

import java.util.Scanner;

import psyduck.command.Command;
import psyduck.command.AddDeadlineCommand;
import psyduck.command.AddEventCommand;
import psyduck.command.AddTodoCommand;
import psyduck.command.DeleteCommand;
import psyduck.command.InvalidCommand;
import psyduck.command.ListCommand;
import psyduck.command.MarkCommand;
import psyduck.command.UnmarkCommand;
import psyduck.ui.UI;

public class Parser {

    private static final Scanner userInputScanner = new Scanner(System.in);

    /**
     * Creates Command Object corresponding to the user's input
     * @param userInput
     * @return Command Object
     */
    public static Command chooseCommand(String userInput) {
    //change back to the starts with version
        String details;
        if (userInput.equals("list")) {
            return new ListCommand();
        }
        if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput);
        }
        if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(userInput);
        }
        if (userInput.startsWith("deadline")) {
            return new AddDeadlineCommand(userInput);
        }
        if (userInput.startsWith("event")) {
            return new AddEventCommand(userInput);
        }
        if (userInput.startsWith("todo")) {
            return new AddTodoCommand(userInput);
        }
        if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        }
        return new InvalidCommand();
    }

    /**
     * Requests User for Input
     * @return
     */
    public static String getUserInput() {
        UI.printDashes();
        String userInput;
        System.out.print("Me: ");
        userInput = userInputScanner.nextLine();
        userInput = userInput.trim();
        UI.printDashes();
        return userInput;
    }

}

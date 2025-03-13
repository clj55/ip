package psyduck.ui;

import java.util.Map;

import psyduck.command.AddDeadlineCommand;
import psyduck.command.AddEventCommand;
import psyduck.command.AddTodoCommand;
import psyduck.command.DeleteCommand;
import psyduck.command.MarkCommand;
import psyduck.command.UnmarkCommand;

public class UI {
    public static final Map<Class<?>, String> commandFormats = Map.of(
            AddTodoCommand.class, "todo [description]",
            AddDeadlineCommand.class, "deadline [description] /[by datetime]",
            AddEventCommand.class, "event [description] /[from datetime] /[to datetime]",
            DeleteCommand.class, "delete [Numeric Task Number]",
            MarkCommand.class, "mark [Numeric Task Number]",
            UnmarkCommand.class, "unmark [Numeric Task Number]"
    );

    public static void printCommandFormat(Class<?> command) {
        System.out.println("Please enter in format: " + commandFormats.get(command));
    }

    public static void printDashes() {
        System.out.println("--------------------------------------------");
    }

    public static void printIntro() {
        System.out.println("Hello! I'm Psyduck");
        System.out.println("PSYDUCK?");
    }

    public static void printOutro() {
        System.out.println("PSYYYYDUCKKKK");
        printDashes();
    }


}

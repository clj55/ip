package psyduck.command;

import static psyduck.Psyduck.taskList;

import psyduck.task.Task;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String userInput) {
        keyword = userInput.substring(4).trim();
    }

    public CommandResult execute() {
        if (keyword.isEmpty()) {
            System.out.println("What do you want to find?");
            return null;
        }
        String matches = "";
        int matchCount = 0;
        for (Task s : taskList) {
            String taskName = s.getTaskName();
            if (taskName.contains(keyword)) {
                matchCount++;
                matches = matches.concat(matchCount + ". " + s + "\n");
            }
        }
        if (matchCount == 0) {
            System.out.println("No matches found");
            return null;
        }2
        System.out.println("Here are the matching tasks:");
        System.out.print(matches);

        return null;
    }
}

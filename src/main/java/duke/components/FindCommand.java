package duke.components;

import duke.command.Task;
import duke.exception.EmptyEventException;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String userInput;
    public FindCommand(String fullCommand) {
        userInput = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String toFind = userInput.replace("find", "").trim();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task: tasks.getTasks()) {
                if (task.getTitle().contains(toFind)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.size() < 1) {
                ui.showError("No tasks matching '" + toFind + "'");
            } else {
                ui.printList(matchingTasks, "Here are the matching tasks in your list:");
            }

        } catch (StringIndexOutOfBoundsException e) {
            ui.showError(EmptyEventException.errorMessage());
        }
    }
}

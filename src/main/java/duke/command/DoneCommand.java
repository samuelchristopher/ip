package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

public class DoneCommand extends Command {
    private String userInput;
    private String[] userInputArray;

    public DoneCommand(String fullCommand) {
        userInput = fullCommand;
        userInputArray = fullCommand.split(" ");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskId = Integer.parseInt(userInputArray[1]);
            tasks.markAsDone(taskId);
        } catch (NullPointerException e) {
            ui.showError("☹ OOPS!!! A task with that ID does not exist.");
        }  catch (IndexOutOfBoundsException e) {
            ui.showError("☹ OOPS!!! A task with that ID does not exist.");
        }
    }

    public boolean isExit() {
        return false;
    }
}

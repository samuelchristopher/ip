package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exception.EmptyDeadlineException;

public class DeadlineCommand extends Command {
    private String userInput;

    public DeadlineCommand(String fullCommand) {
        userInput = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int deadlineDateIndex = userInput.indexOf("/by");
            String title = userInput.substring(0, deadlineDateIndex).replace("deadline", "");
            String deadlineDate = userInput.substring(deadlineDateIndex + 3, userInput.length());
            Task newTask = tasks.addDeadline(title.trim(), deadlineDate.trim());
            ui.printSuccessfulAddMessage(newTask);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showError(EmptyDeadlineException.errorMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}

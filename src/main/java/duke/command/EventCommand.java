package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exception.EmptyEventException;

public class EventCommand extends Command {
    private String userInput;

    public EventCommand(String fullCommand) {
        userInput = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int atIndex = userInput.indexOf("/at");
            String title = userInput.substring(0, atIndex).replace("event", "");
            String atDate = userInput.substring(atIndex+3, userInput.length());
            Task newTask = tasks.addEvent(title.trim(), atDate.trim());
            ui.printSuccessfulAddMessage(newTask);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showError(EmptyEventException.errorMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}

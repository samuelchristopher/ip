package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

public class TodoCommand extends Command {
    private String userInput;

    public TodoCommand(String fullCommand) {
        userInput = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String todo = userInput.replace("todo", "").trim();
        Task newTask = TaskList.addTodo(todo);
        Ui.printSuccessfulAddMessage(newTask);
    }

    public boolean isExit() {
        return false;
    }
}

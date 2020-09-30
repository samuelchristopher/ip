package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printList(tasks.getItems(), "Here are the tasks:");
    }
}

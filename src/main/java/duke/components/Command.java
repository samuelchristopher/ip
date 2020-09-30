package duke.components;

import duke.exception.DukeException;

/**
 * Command represents a command that user has input.
 * Each command has an execute function that will be called.
 * The isExit flag is to identify the exit command.
 *
 */
public class Command {
    public Command() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return false;
    }
}

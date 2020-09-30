import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.components.Parser;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        storage.save(tasks.getTasks());
    }

    public static void main(String[] args) throws DukeException{
        new Duke("tasks.txt").run();
    }
}
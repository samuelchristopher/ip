package duke.exception;

public class EmptyEventException extends DukeException {
    public static String errorMessage() {
        return "☹ OOPS!!! Event description or timing cannot be empty. \n" +
                "Please use event <event name> /at <event timing>.";
    }
}

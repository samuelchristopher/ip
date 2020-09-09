public class EmptyDeadlineException extends DukeException {
    public static String errorMessage() {
        return "☹ OOPS!!! Deadline description or due date cannot be empty. \n" +
                "Please use deadline <task name> /by <deadline>.";
    }
}

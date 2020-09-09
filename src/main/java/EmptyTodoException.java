public class EmptyTodoException extends DukeException {
    public static String errorMessage() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}

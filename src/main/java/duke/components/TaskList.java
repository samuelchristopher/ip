package duke.components;
import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;
import duke.exception.DukeException;
import duke.exception.EmptyTodoException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> items = new ArrayList<>();

    public TaskList(ArrayList<Task> savedItems) throws DukeException {
        for (Task task : savedItems) {
            items.add(task);
        }
    }

    public TaskList() {
    }

    public static ArrayList<Task> getItems() {
        return items;
    }

    public static void addTodo(String item, boolean isCompleted) {
        Task newTask = addTodo(item);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    public static Task addTodo(String item) {
        try {
            if (item.isEmpty()) {
                throw new EmptyTodoException();
            }
            Task newTask = new Todo(item);
            items.add(newTask);
            return newTask;
        } catch (EmptyTodoException e) {
//            separator();
            System.out.println(EmptyTodoException.errorMessage());
//            separator();
        }

        return new Task("");
    }

    public static void addDeadline(String item, String deadline, boolean isCompleted) throws StringIndexOutOfBoundsException {
        Task newTask = addDeadline(item, deadline);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    public static Task addDeadline(String item, String deadline) throws StringIndexOutOfBoundsException {
        Task newTask = new Deadline(item, deadline);
        items.add(newTask);
        return newTask;
    }

    public static void addEvent(String item, String at, boolean isCompleted) {
        Task newTask = addEvent(item, at);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    public static Task addEvent(String item, String at) {
        Task newTask = new Event(item, at);
        items.add(newTask);
        return newTask;
    }

    public static ArrayList<Task> getTasks() {
        return items;
    }

    public static void delete(int taskId) {
        Task task = items.get(taskId - 1);
        items.remove(task);
        Ui.showLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + task.getTitle());
        System.out.println("Now you have " + items.size() + " tasks in the list.");
        Ui.showLine();
    }

    public static void markAsDone(int taskId) {
        Task selectedTask = items.get(taskId - 1);
        selectedTask.markAsCompleted();
        Ui.showLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" " + selectedTask.getTitle());
        Ui.showLine();
    }
}

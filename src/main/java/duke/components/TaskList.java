package duke.components;
import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;
import duke.exception.DukeException;
import duke.exception.EmptyTodoException;

import java.util.ArrayList;

/**
 * TaskList handles all the interactions pertaining to tasks during run time
 * which include adding tasks (todos, events and deadlines), marking them as done,
 * and deleting tasks.
 *
 * In short, TaskList manages tasks.
 */
public class TaskList {
    private static ArrayList<Task> items = new ArrayList<>();

    /**
     * Initialises TaskList items
     *
     * @param savedItems  comes from Storage.load() which returns
     *                    tasks from the text file in the form of
     *                    ArrayList<Task>
     * @throws DukeException
     */
    public TaskList(ArrayList<Task> savedItems) throws DukeException {
        for (Task task : savedItems) {
            items.add(task);
        }
    }

    public TaskList() {
    }

    /**
     * Adds a Todo to TaskList with the option of setting the status
     * of the Todo immediately
     *
     * @param item the Todo's title
     * @param isCompleted the status of the Todo
     * @return void
     */
    public static void addTodo(String item, boolean isCompleted) {
        Task newTask = addTodo(item);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    /**
     * Adds a Todo to TaskList
     *
     * @param item the Todo's title
     * @return Task the new Todo that has been added
     * @throws EmptyTodoException if there is no description / title provided
     */
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

    /**
     * Adds a Deadline to TaskList with the option of setting the status
     * of the Deadline immediately
     *
     * @param item the Deadline's title
     * @param deadline the deadline for the Task ("/by" field)
     * @param isCompleted the status of the Deadline
     * @return void
     */
    public static void addDeadline(String item, String deadline, boolean isCompleted) throws StringIndexOutOfBoundsException {
        Task newTask = addDeadline(item, deadline);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    /**
     * Adds a Deadline to TaskList
     *
     * @param item the Deadline's title
     * @param deadline the deadline for the Task ("/by" field)
     * @return Task the deadline that has been newly added
     * @throws StringIndexOutOfBoundsException if title or "/by" is not provided
     */
    public static Task addDeadline(String item, String deadline) throws StringIndexOutOfBoundsException {
        Task newTask = new Deadline(item, deadline);
        items.add(newTask);
        return newTask;
    }

    /**
     * Adds a Event to TaskList with the option of setting the status
     * of the Event immediately
     *
     * @param item the Event's title
     * @param at the timing for the Event ("/at" field)
     * @param isCompleted the status of the Event
     * @return void
     */
    public static void addEvent(String item, String at, boolean isCompleted) {
        Task newTask = addEvent(item, at);
        if (isCompleted) {
            newTask.markAsCompleted();
        }
    }

    /**
     * Adds a Event to TaskList
     *
     * @param item the Event's title
     * @param at the timing for the Event ("/at" field)
     * @return Task the event that has been newly added
     */
    public static Task addEvent(String item, String at) {
        Task newTask = new Event(item, at);
        items.add(newTask);
        return newTask;
    }

    public static ArrayList<Task> getTasks() {
        return items;
    }

    /**
     * Deletes a task with taskId and prints a message
     * to inform user that the task has been deleted
     *
     * @param taskId ID of the task to be deleted
     * @return void
     */
    public static void delete(int taskId) {
        Task task = items.get(taskId - 1);
        items.remove(task);
        Ui.showLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + task.getTitle());
        System.out.println("Now you have " + items.size() + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Marks a task with taskId as done and prints a message
     * to inform the user that the status of the task has
     * been updated
     *
     * @param taskId ID of the task to be marked 'done'
     * @return void
     */
    public static void markAsDone(int taskId) {
        Task selectedTask = items.get(taskId - 1);
        selectedTask.markAsCompleted();
        Ui.showLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" " + selectedTask.getTitle());
        Ui.showLine();
    }
}

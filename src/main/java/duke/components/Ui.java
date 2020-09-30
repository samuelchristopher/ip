package duke.components;

import duke.command.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class that handles all user interaction aspect of Duke.
 * These include reading input and also printing different
 * forms of output.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);

    /**
     * Prints "hello from duke" welcome message to start
     * interaction with user
     *
     * @return void
     */
    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }

    /**
     * Used to print successful message when a Task (Todo, Event or Deadline)
     * has been successfully added to the TaskList
     *
     * @param newTask  Task object that has just been added
     * @return void
     */
    public static void printSuccessfulAddMessage(Task newTask) {
        showLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask.getTitle());
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Prints ordered lists of tasks (1,2,3 ...)
     *
     * @param items  ArrayList of tasks to be printed
     * @return void
     */
    public static void printList(ArrayList<Task> items) {
        int i = 0;
        showLine();
        if (Task.numberOfTasks == 0) {
            System.out.println("No tasks have been added");
        } else {
            for (Task currentTask : items) {
                System.out.println((items.indexOf(currentTask) + 1) + ". " + currentTask.getTitle());
            }
        }
        showLine();
    }

    /**
     * Prints out the command that has just been keyed in by user.
     *
     * @param command String which is the command
     * @return void
     */
    public static void echo(String command) {
        showLine();
        System.out.println(command);
        showLine();
    }

    /**
     * Reads the next line of user input to help
     * help Duke determine which command should be executed
     *
     * @return String line that has been read
     */
    public static String readCommand() {
        return in.nextLine();
    }

    /**
     * Helper function that can be used to print
     * "_______" line in the console
     *
     * @return void
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greeting to the user
     *
     * @return void
     */
    public static void greet() {
        showLine();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints the exit message when the user wants to exit Duke
     *
     * @return void
     */
    public static void exit() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Helper function to inform user that there has been
     * a loading error
     *
     * @return void
     */
    public static void showLoadingError() {
        System.out.println("Error");
        showLine();
    }

    /**
     * Helper function to print error messages with lines (______)
     * above and below the error message
     *
     * @param message String which is the error message
     * @return void
     */
    public static void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}

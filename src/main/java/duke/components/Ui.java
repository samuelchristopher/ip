package duke.components;

import duke.command.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }

    public static void printSuccessfulAddMessage(Task newTask) {
        showLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask.getTitle());
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
        showLine();
    }

    public static void printList(ArrayList<Task> items, String message) {
        int i = 0;
        showLine();
        if (Task.numberOfTasks == 0) {
            System.out.println("No tasks have been added");
        } else {
            System.out.println(message);
            for (Task currentTask : items) {
                System.out.println((items.indexOf(currentTask) + 1) + ". " + currentTask.getTitle());
            }
        }
        showLine();
    }

    public static void echo(String command) {
        showLine();
        System.out.println(command);
        showLine();
    }

    public static String readCommand() {
        return in.nextLine();
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        showLine();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        showLine();
    }

    public static void exit() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public static void showLoadingError() {
        System.out.println("Error");
        showLine();
    }

    public static void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}

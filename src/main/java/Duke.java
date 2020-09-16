import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;
import duke.exception.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];
    private static File f = new File("tasks.txt");
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        boolean shouldExit = false;
        Scanner in = new Scanner(System.in);
        initData();
        while(!shouldExit) {
            String userInput = in.nextLine();
            String[] userInputArray = (userInput.split(" "));
            // first item in userInputArray is the command. e.g. "add" will be stored in command from "add this task"
            String command = userInputArray[0];
            if (command.equals("bye")) {
                shouldExit = true;
                separator();
                break;
            }

            if (command.equals("list")) {
                list();
            } else if (command.equals("done")) {
                try {
                    int taskId = Integer.parseInt(userInputArray[1]);
                    markAsDone(taskId);
                } catch (NullPointerException e) {
                    separator();
                    System.out.println("☹ OOPS!!! A task with that ID does not exist.");
                    separator();
                }  catch (ArrayIndexOutOfBoundsException e) {
                    separator();
                    System.out.println("☹ OOPS!!! Please specify a task id.");
                    separator();
                }
            } else if (command.equals("todo")) {
                String todo = userInput.replace("todo", "").trim();
                Task newTask = addTodo(todo);
                printSuccessfulAddMessage(newTask);
            } else if (command.equals("deadline")) {
                try {
                    int deadlineDateIndex = userInput.indexOf("/by");
                    String title = userInput.substring(0, deadlineDateIndex).replace("deadline", "");
                    String deadlineDate = userInput.substring(deadlineDateIndex + 3, userInput.length());
                    Task newTask = addDeadline(title.trim(), deadlineDate.trim());
                    printSuccessfulAddMessage(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    separator();
                    System.out.println(EmptyDeadlineException.errorMessage());
                    separator();
                }
            } else if (command.equals("event")) {
                try {
                    int atIndex = userInput.indexOf("/at");
                    String title = userInput.substring(0, atIndex).replace("event", "");
                    String atDate = userInput.substring(atIndex+3, userInput.length());
                    Task newTask = addEvent(title.trim(), atDate.trim());
                    printSuccessfulAddMessage(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    separator();
                    System.out.println(EmptyEventException.errorMessage());
                    separator();
                }
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    separator();
                    System.out.println(InvalidCommandException.errorMessage());
                    separator();
                }
            }

        }
        save();
        exit();
    }


    public static void save() {
        try {
            // empty current saved items;
            FileWriter clear = new FileWriter(f);
            clear.write("");
            clear.close();
            int i = 0;
            for(i = 0; i < Task.numberOfTasks; i++) {
                Task task = items[i];

                FileWriter fw = new FileWriter(f, true);

                fw.write(task.saveFormat() + "\n");
                fw.close();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void initData() {
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] items = currentLine.split(" | ");
                String typeOfTask = items[0];
                boolean isCompleted = items[2].equals("1") ? true : false;
                // remove "T |"
                currentLine = currentLine.replace(typeOfTask + " | ", "");
                // remove completed status
                currentLine = currentLine.replace(items[2] + " | ", "");
                if (typeOfTask.trim().equalsIgnoreCase("T")) {
                    addTodo(currentLine, isCompleted);
                }
                if (typeOfTask.trim().equalsIgnoreCase("E")) {
                    int atIndex = currentLine.indexOf("|");
                    String title = currentLine.substring(0, atIndex - 1).trim();
                    String at = currentLine.substring(atIndex + 1).trim();

                    addEvent(title, at, isCompleted);
                }
                if (typeOfTask.trim().equalsIgnoreCase("D")) {
                    int byIndex = currentLine.indexOf("|");
                    String title = currentLine.substring(0, byIndex - 1).trim();
                    String by = currentLine.substring(byIndex + 1).trim();
                    addDeadline(title, by, isCompleted);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markAsDone(int taskId) {

        Task selectedTask = items[taskId - 1];
        selectedTask.markAsCompleted();
        separator();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" " + selectedTask.getTitle());
        separator();
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
            items[Task.numberOfTasks - 1] = newTask;
            return newTask;
        } catch (EmptyTodoException e) {
            separator();
            System.out.println(EmptyTodoException.errorMessage());
            separator();
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
        items[Task.numberOfTasks - 1] = newTask;
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
        items[Task.numberOfTasks - 1] = newTask;
        return newTask;
    }

    public static void printSuccessfulAddMessage(Task newTask) {
        separator();
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask.getTitle());
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
        separator();
    }

    public static void list() {
        int i = 0;
        separator();
        if (Task.numberOfTasks == 0) {
            System.out.println("No tasks have been added");
        } else {
            for(i = 0; i < Task.numberOfTasks; i++) {
                Task currentTask = items[i];
                System.out.println((i + 1) + ". " + currentTask.getTitle());
            }
        }
        separator();
    }

    public static void echo(String command) {
        separator();
        System.out.println(command);
        separator();
    }

    public static void separator() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        separator();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        separator();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        separator();
    }
}
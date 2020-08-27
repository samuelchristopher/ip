import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        boolean shouldExit = false;
        Scanner in = new Scanner(System.in);
        while(!shouldExit) {
            String userInput = in.nextLine();
            String[] userInputArray = (userInput.split(" "));
            String command = userInputArray[0];
            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    list();
                } else if (command.equals("done")) {
                    markAsDone(Integer.parseInt(userInputArray[1]));
                } else {
                    add(userInput);
                }
            } else {
                shouldExit = true;
                separator();
            }
        }
        exit();
    }

    public static void markAsDone(int taskId) {
        Task selectedTask = items[taskId - 1];
        selectedTask.markAsCompleted();
        separator();
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("[✓] " + selectedTask.getTitle());
        separator();
    }

    public static void add(String item) {
        Task newTask = new Task(item);
        items[Task.numberOfTasks - 1] = newTask;
        separator();
        System.out.println("added: " + newTask.getTitle());
        separator();
    }

    public static void list() {
        int i = 0;
        separator();
        for(i = 0; i < Task.numberOfTasks; i++) {
            Task currentTask = items[i];
            System.out.println((i + 1) + ". ["+ (currentTask.completed ? "✓" : "✗") +"] " + currentTask.getTitle());
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

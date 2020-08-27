import java.util.Scanner;

public class Duke {
    private static String[] items = new String[100];
    private static int numberOfItems = 0;

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
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    list();
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

    public static void add(String item) {
        items[numberOfItems] = item;
        numberOfItems++;
        separator();
        System.out.println("added: " + item);
        separator();
    }

    public static void list() {
        int i = 0;
        separator();
        for(i = 0; i < numberOfItems; i++) {
            System.out.println((i + 1) + ". " + items[i]);
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

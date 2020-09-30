package duke.components;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles Duke's file interactions (to ensure persistence
 * of data). These include the initialisation of Duke (reading from the text file)
 * and the saving of data (writing and updating the text file).
 */
public class Storage {
    private static File f;
    private static ArrayList<Task> temp = new ArrayList<>();

    public Storage(String filePath) {
        f = new File("tasks.txt");
    }

    /**
     * Loads all the tasks that have been stored on the text file
     * into an ArrayList of tasks that can be used to initialize the
     * TaskList. load() is called at the start of every instance of Duke.
     *
     * @return ArrayList<Task> this ArrayList is used to initialize the TaskList
     */
    public static ArrayList<Task> load() {
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] items = currentLine.split(" | ");
                String typeOfTask = items[0];
                boolean isCompleted = items[2].equals("1") ? true : false;
                Task newTask = null;
                // remove "T |"
                currentLine = currentLine.replace(typeOfTask + " | ", "");
                // remove completed status
                currentLine = currentLine.replace(items[2] + " | ", "");
                if (typeOfTask.trim().equalsIgnoreCase("T")) {
                    newTask = new Todo(currentLine);
                } else if (typeOfTask.trim().equalsIgnoreCase("E")) {
                    int atIndex = currentLine.indexOf("|");
                    String title = currentLine.substring(0, atIndex - 1).trim();
                    String at = currentLine.substring(atIndex + 1).trim();
                    newTask = new Event(title, at);
                } else if (typeOfTask.trim().equalsIgnoreCase("D")) {
                    int byIndex = currentLine.indexOf("|");
                    String title = currentLine.substring(0, byIndex - 1).trim();
                    String by = currentLine.substring(byIndex + 1).trim();
                    newTask = new Deadline(title, by);
                }

                temp.add(newTask);
                if (isCompleted) {
                    newTask.markAsCompleted();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return temp;
    }

    /**
     * Saves the current tasks in TaskList into the text file.
     * save() is called at the end of every instance of Duke.
     *
     * @param items the ArrayList<Task> of current tasks in the TaskList
     * @return void
     */
    public static void save(ArrayList<Task> items) {
        try {
            // empty current saved items;
            FileWriter clear = new FileWriter(f);
            clear.write("");
            clear.close();
            int i = 0;
            for(i = 0; i < items.size(); i++) {
                Task task = items.get(i);

                FileWriter fw = new FileWriter(f, true);

                fw.write(task.saveFormat() + "\n");
                fw.close();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

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

public class Storage {
    private static File f;
    private static ArrayList<Task> temp = new ArrayList<>();

    public Storage(String filePath) {
        f = new File("tasks.txt");
    }

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

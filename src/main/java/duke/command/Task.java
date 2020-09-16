package duke.command;

public class Task {
    public static int numberOfTasks = 0;
    public static Task[] all = new Task[100];
    public boolean isCompleted = false;
    public String title = "";

    public Task(String title) {
        this.title = title;
        numberOfTasks++;
    }

    public String saveFormat() {
        return title;
    }

    public int getSaveStatus() {
        return isCompleted ? 1 : 0;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return "["+ (isCompleted ? "✓" : "✗") +"]";
    }

    public String getType() {
        return " ";
    }

    public String getByString() {
        return " ";
    }
}

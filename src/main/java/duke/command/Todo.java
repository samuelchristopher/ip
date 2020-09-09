package duke.command;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTitle() {
        return this.getType() + this.getStatus() + " " + title;
    }


    public String getType() {
        return "[T]";
    }
}

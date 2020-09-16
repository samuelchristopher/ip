package duke.command;

public class Deadline extends Task {
    // test comment
    public String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String saveFormat() {
        return "D | " + (this.getSaveStatus()) + " | " + title + " | " + deadline;
    }

    public String getTitle() {
        return this.getType() + this.getStatus() + " " + title + " " + this.getByString();
    }

    public String getType() {
        return "[D]";
    }

    public String getByString() {
        return "(by: " + deadline + ")";
    }
}

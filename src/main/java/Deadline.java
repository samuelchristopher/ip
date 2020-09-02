public class Deadline extends Task {
    public String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String getTitle() {
        return title + " " + this.getByString();
    }

    public String getType() {
        return "[D]";
    }

    public String getByString() {
        return "(by: " + deadline + ")";
    }
}

package duke.command;

public class Event extends Task {
    public String at;
    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    public String getTitle() {
        return this.getType() + this.getStatus() + " " + title + " " + this.getAtString();
    }

    public String getType() {
        return "[E]";
    }

    public String getAtString() {
        return "(at: " + at + ")";
    }
}

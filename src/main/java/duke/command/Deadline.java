package duke.command;

import duke.components.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String saveFormat() {
        return "D | " + (this.getSaveStatus()) + " | " + title + " | " + deadline;
    }

    public String formattedDate() {
        String[] dateComponents = deadline.split(" ");
        LocalDate deadlineDate = LocalDate.parse(dateComponents[0]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return deadlineDate.format(formatter) + (dateComponents.length > 1 ? (" " + dateComponents[1]) : "");
    }

    public String getTitle() {
        return this.getType() + this.getStatus() + " " + title + " " + this.getByString();
    }

    public String getType() {
        return "[D]";
    }

    public String getByString() {
        return "(by: " + this.formattedDate() + ")";
    }
}

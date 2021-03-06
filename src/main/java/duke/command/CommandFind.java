package duke.command;

import java.util.LinkedList;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The command to find tasks with search keyword.
 */
public class CommandFind implements Command {
    private final String keyword;
    private String message = "";

    /**
     * Construct a new find command with search keyword.
     * @param keyword the string to search
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command to search all tasks with keyword.
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> object to handle interface
     */
    public void execute(TaskList tasks, Ui ui) {
        LinkedList<Task> results = new LinkedList<Task>();
        for (Task result : tasks.getList()) {
            if (result.getDescription().contains(keyword)) {
                results.add(result);
            }
        }
        if (results.size() == 0) {
            ui.printLine("No tasks containing " + keyword + " found!");
            message += "No tasks containing " + keyword + " found!";
        } else {
            ui.printLine("Found " + results.size() + " tasks:");
            ui.printList(results);
            message += "Found " + results.size() + " tasks:\n";
            message += "Here are the tasks in your list:\n";
            for (int i = 1; i <= results.size(); i++) {
                message += i + "." + results.get(i - 1) + "\n";
            }
        }
    }

    /**
     * Return <code>false</code> since the command is not exit.
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    public String getMessage() {
        return message;
    }
}

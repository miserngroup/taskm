package pl.misern.taskm.event.command.dialog;

import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;

public class DisposeTaskListDialogCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().getAssignedTasksDialog().dispose();
	}
}

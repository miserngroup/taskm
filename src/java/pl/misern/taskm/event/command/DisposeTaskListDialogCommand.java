package pl.misern.taskm.event.command;

import pl.misern.taskm.gui.Window;

public class DisposeTaskListDialogCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().getTasksDialog().dispose();
	}
}

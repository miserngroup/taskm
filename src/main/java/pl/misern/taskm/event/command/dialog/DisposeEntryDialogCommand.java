package pl.misern.taskm.event.command.dialog;

import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;

public class DisposeEntryDialogCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().getEntryDialog().dispose();
	}
}

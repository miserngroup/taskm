package pl.misern.taskm.event.command;

import pl.misern.taskm.gui.Window;

public class DisposeEntryDialogCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().getDialog().dispose();
	}
}

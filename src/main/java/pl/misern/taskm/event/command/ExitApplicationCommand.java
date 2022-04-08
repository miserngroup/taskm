package pl.misern.taskm.event.command;

import pl.misern.taskm.gui.Window;

public class ExitApplicationCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().dispose();
		System.exit(0);
	}
}

package pl.misern.taskm.event.command;

import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;

public class NewFileStateCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().setSaved(false);
		Window.getInstance().setTitle("Task Manager — Nowy plik");
		Window.getInstance().resetState();
	}
}

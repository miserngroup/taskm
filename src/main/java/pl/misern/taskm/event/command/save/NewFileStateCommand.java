package pl.misern.taskm.event.command.save;

import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;

public class NewFileStateCommand implements Command {
	@Override
	public void execute() {
		Window.getInstance().setSaved(false);
		Window.getInstance().changeLanguage(Window.getInstance().getLocale());
		Window.getInstance().resetState();
	}
}

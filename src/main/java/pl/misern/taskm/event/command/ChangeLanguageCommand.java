package pl.misern.taskm.event.command;

import pl.misern.taskm.gui.Window;

import java.util.Locale;

public record ChangeLanguageCommand(Locale currentLocale) implements Command {
	@Override
	public void execute() {
		Window.getInstance().changeLanguage(currentLocale);
	}
}

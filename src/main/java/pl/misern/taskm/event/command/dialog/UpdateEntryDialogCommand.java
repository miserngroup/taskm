package pl.misern.taskm.event.command.dialog;

import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.event.command.UpdateEntryCommand;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.model.Entity;

public class UpdateEntryDialogCommand implements Command {
	@Override
	public void execute() {
		EntryDialog<?> dialog = Window.getInstance().getEntryDialog();
		Entity entity = dialog.prepareEntity();
		EventHandler.pushEvent(new UpdateEntryCommand(entity));
		EventHandler.pushEvent(new DisposeEntryDialogCommand());
	}
}

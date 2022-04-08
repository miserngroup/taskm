package pl.misern.taskm.event.command.dialog;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.AddEntryCommand;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.model.Entity;

@RequiredArgsConstructor
public class SaveEntryDialogCommand implements Command {
	@Override
	public void execute() {
		EntryDialog<?> dialog = Window.getInstance().getEntryDialog();
		Entity entity = dialog.prepareEntity();
		EventHandler.pushEvent(new AddEntryCommand(entity));
		EventHandler.pushEvent(new DisposeEntryDialogCommand());
	}
}

package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.model.Entity;

@RequiredArgsConstructor
public class SaveEntryDialogCommand implements Command {
	@Override
	public void execute() {
		EntryDialog<?> dialog = Window.getInstance().getDialog();
		Entity entity = dialog.prepareEntity();
		EventHandler.pushEvent(new AddEntryCommand(entity));
		EventHandler.pushEvent(new DisposeEntryDialogCommand());
	}
}

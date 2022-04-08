package pl.misern.taskm.event.command.dialog;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.EntryActionType;

@RequiredArgsConstructor
public class ShowEntryDialogCommand implements Command {

	private final Entity entity;
	private final EntryActionType entryActionType;

	@Override
	public void execute() {
		Window.getInstance().showEntryDialog(entity);
		registerDialogEventListeners();
	}

	private void registerDialogEventListeners() {
		if (entryActionType == EntryActionType.SAVE) {
			Window.getInstance().getEntryDialog().getSaveButton().addActionListener(action ->
					EventHandler.pushEvent(new SaveEntryDialogCommand()));
		} else if (entryActionType == EntryActionType.EDIT) {
			Window.getInstance().getEntryDialog().getSaveButton().addActionListener(action ->
					EventHandler.pushEvent(new UpdateEntryDialogCommand()));
		}

		Window.getInstance().getEntryDialog().getExitButton().addActionListener(action ->
				EventHandler.pushEvent(new DisposeEntryDialogCommand()));
	}
}

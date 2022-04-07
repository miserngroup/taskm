package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;

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
			Window.getInstance().getDialog().getSaveButton().addActionListener(action ->
					EventHandler.pushEvent(new SaveEntryDialogCommand()));
		} else if (entryActionType == EntryActionType.EDIT) {
			Window.getInstance().getDialog().getSaveButton().addActionListener(action ->
					EventHandler.pushEvent(new UpdateEntryDialogCommand()));
		}

		Window.getInstance().getDialog().getExitButton().addActionListener(action ->
				EventHandler.pushEvent(new DisposeEntryDialogCommand()));
	}
}

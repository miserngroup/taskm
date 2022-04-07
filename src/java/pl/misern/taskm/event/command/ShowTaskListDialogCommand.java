package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.gui.dialog.AssignedTasksDialog;
import pl.misern.taskm.model.Person;

@RequiredArgsConstructor
public class ShowTaskListDialogCommand implements Command {

	private final int index;
	private Person person;

	@Override
	public void execute() {
		person = Window.getInstance().getMainScreen().getTeamScreen().getPersonAt(index);
		Window.getInstance().showTaskListDialog(person);
		registerDialogEventListeners();
	}

	private void registerDialogEventListeners() {
		AssignedTasksDialog tasksDialog = Window.getInstance().getTasksDialog();
		tasksDialog.getFinishButton().addActionListener(action ->
				EventHandler.pushEvent(new FinishPersonTaskCommand(person, tasksDialog.getActualTask())));

		tasksDialog.getRemoveButton().addActionListener(action ->
				EventHandler.pushEvent(new RemovePersonTaskCommand(person, tasksDialog.getActualTask())));

		tasksDialog.getExitButton().addActionListener(action ->
				EventHandler.pushEvent(new DisposeTaskListDialogCommand()));
	}
}

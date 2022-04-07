package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

@RequiredArgsConstructor
public class UpdateEntryCommand implements Command {
	private final Entity entity;

	@Override
	public void execute() {
		if (entity instanceof Person person) {
			Window.getInstance().getMainScreen().getTeamScreen().updatePerson(person);
			Window.getInstance().setSaved(false);
		} else if (entity instanceof Task task) {
			Window.getInstance().getMainScreen().getTaskScreen().updateTask(task);
			Window.getInstance().setSaved(false);
		}
	}
}

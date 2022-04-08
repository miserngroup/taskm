package pl.misern.taskm.event.command;

import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

public record UpdateEntryCommand(Entity entity) implements Command {
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

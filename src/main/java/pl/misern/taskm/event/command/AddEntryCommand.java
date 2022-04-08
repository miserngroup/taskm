package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

@RequiredArgsConstructor
public class AddEntryCommand implements Command {

	private final Entity entity;
	private final Window window = Window.getInstance();

	@Override
	public void execute() {
		if (entity instanceof Person person) {
			window.getMainScreen().getTeamScreen().addPerson(person);
			window.setSaved(false);
		} else if (entity instanceof Task task) {
			window.getMainScreen().getTaskScreen().addTask(task);
			window.setSaved(false);
		}
	}
}

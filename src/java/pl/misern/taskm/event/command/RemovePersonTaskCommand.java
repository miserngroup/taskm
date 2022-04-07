package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

@RequiredArgsConstructor
public class RemovePersonTaskCommand implements Command {

	private final Person person;
	private final Task actualTask;

	@Override
	public void execute() {
		person.getTasks().removeIf(task -> task.equals(actualTask));
		Window.getInstance().getTasksDialog().loadTasks();
		Window.getInstance().getMainScreen().getTaskScreen().addTask(actualTask);
		Window.getInstance().setSaved(false);
	}
}

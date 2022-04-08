package pl.misern.taskm.event.command.task;

import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

import java.util.Objects;

public record FinishPersonTaskCommand(Person person,
									  Task actualTask) implements Command {

	@Override
	public void execute() {
		person.getTasks().removeIf(task -> task.equals(actualTask));
		Window.getInstance().getAssignedTasksDialog().loadTasks();
		Window.getInstance().setSaved(false);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (FinishPersonTaskCommand) obj;
		return Objects.equals(this.person, that.person) &&
				Objects.equals(this.actualTask, that.actualTask);
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, actualTask);
	}

	@Override
	public String toString() {
		return "FinishPersonTaskCommand[" +
				"person=" + person + ", " +
				"actualTask=" + actualTask + ']';
	}

}

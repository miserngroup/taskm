package pl.misern.taskm.event.command.task;

import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.AddEntryCommand;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Objects;
import java.util.ResourceBundle;

public record AssignTaskCommand(Task task) implements Command {
	@Override
	public void execute() {
		final Window window = Window.getInstance();
		final JList<Person> team = window.getMainScreen().getTeamScreen().getPeopleList();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Window.getInstance().getLocale());

		if (team.getModel().getSize() == 0) {
			handleError(bundle);
		} else {
			for (int i = 0; i < team.getModel().getSize(); i++) {
				Person person = team.getModel().getElementAt(i);
				int decision = JOptionPane.showConfirmDialog(Window.getInstance(),
						bundle.getString("dialog.confirm.text").replace("%t", task.getName()).replace("%u", person.getName()),
						bundle.getString("dialog.confirm"),
						JOptionPane.YES_NO_OPTION);
				if (decision == JOptionPane.YES_OPTION) {
					person.getTasks().add(task);
					window.getMainScreen().getTeamScreen().removePerson(person);
					window.getMainScreen().getTaskScreen().removeTask(task);
					EventHandler.pushEvent(new AddEntryCommand(person));

					break;
				}
			}
		}
	}

	private void handleError(ResourceBundle bundle) {
		JOptionPane.showMessageDialog(Window.getInstance(),
				bundle.getString("error.assign"),
				bundle.getString("error.title"),
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (AssignTaskCommand) obj;
		return Objects.equals(this.task, that.task);
	}

	@Override
	public int hashCode() {
		return Objects.hash(task);
	}
}

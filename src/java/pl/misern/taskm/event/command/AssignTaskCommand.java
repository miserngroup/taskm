package pl.misern.taskm.event.command;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.AddEntryCommand;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

import javax.swing.JList;
import javax.swing.JOptionPane;

@RequiredArgsConstructor
public class AssignTaskCommand implements Command {

	private final Task task;
	private final JList<Person> team;

	@Override
	public void execute() {
		if (team.getModel().getSize() == 0) {
			JOptionPane.showMessageDialog(Window.getInstance(),
					"Nie można przypisać — dodaj przynajmniej jedną osobę",
					"Błąd",
					JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i = 0; i < team.getModel().getSize(); i++) {
				Person person = team.getModel().getElementAt(i);
				int decision = JOptionPane.showConfirmDialog(Window.getInstance(),
						"Czy przypisać task " + task.getName() + " do " + person.getName() + "?",
						"Potwierdź",
						JOptionPane.YES_NO_OPTION);
				if (decision == JOptionPane.YES_OPTION) {
					person.getTasks().add(task);
					Window.getInstance().getMainScreen().getTeamScreen().removePerson(person);
					Window.getInstance().getMainScreen().getTaskScreen().removeTask(task);
					EventHandler.pushEvent(new AddEntryCommand(person));

					break;
				}
			}
		}
	}
}

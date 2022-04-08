package pl.misern.taskm.gui.widget;

import lombok.Getter;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Task;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.util.ArrayList;
import java.util.List;

public class TaskList extends JList<Task> {

	private final DefaultListModel<Task> taskListModel = new DefaultListModel<>();

	@Getter
	private Task selectedTask = new Task();

	public TaskList() {
		setModel(taskListModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addListSelectionListener(action ->
				selectedTask = (getSelectedIndex() >= 0) ? getSelectedValue() : selectedTask);
	}

	public void addTask(Task task) {
		taskListModel.addElement(task);
	}

	public void updateTask(Task task) {
		selectedTask.setName(task.getName());
	}

	public void removeTask(int index) {
		if (index != -1) {
			taskListModel.remove(index);
			Window.getInstance().setSaved(false);
		}
	}

	public void removeTask(Task task) {
		int index = taskListModel.indexOf(task);
		removeTask(index);
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < taskListModel.getSize(); i++) {
			tasks.add(taskListModel.getElementAt(i));
		}

		return tasks;
	}

	public void removeAllElements() {
		taskListModel.removeAllElements();
	}
}

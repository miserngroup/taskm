package pl.misern.taskm.gui.screen;

import lombok.Getter;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Task;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskScreen extends JPanel {

	private final JList<Task> taskList;
	private final DefaultListModel<Task> taskListModel;

	private final JButton addTask = new JButton("Dodaj");
	private final JButton assignTask = new JButton("Przypisz");
	private final JButton removeTask = new JButton("Usuń");

	private Task actualTask = new Task();

	public TaskScreen() {
		setBorder(new TitledBorder("Zadania"));
		setLayout(new BorderLayout());

		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(action -> {
			if (taskList.getSelectedIndex() >= 0) {
				actualTask = taskList.getSelectedValue();
				assignTask.setEnabled(true);
				removeTask.setEnabled(true);
			} else {
				assignTask.setEnabled(false);
				removeTask.setEnabled(false);
			}
		});

		assignTask.setEnabled(false);
		assignTask.setContentAreaFilled(false);

		removeTask.setEnabled(false);
		removeTask.setContentAreaFilled(false);
		removeTask.addActionListener(action -> {
			int selectedIndex = taskList.getSelectedIndex();
			removeTask(selectedIndex);
		});

		JPanel personButtonPanel = new JPanel(new GridLayout(1, 3));
		personButtonPanel.add(addTask);
		personButtonPanel.add(assignTask);
		personButtonPanel.add(removeTask);

		add(taskList, BorderLayout.CENTER);
		add(personButtonPanel, BorderLayout.SOUTH);
	}

	public void addTask(Task task) {
		taskListModel.addElement(task);
		repaint();
	}

	public void updateTask(Task task) {
		actualTask.setName(task.getName());
		repaint();
	}

	public void removeTask(int index) {
		if (index != -1) {
			taskListModel.remove(index);
			Window.getInstance().setSaved(false);
		}
		repaint();
	}

	public void removeTask(Task task) {
		int index = -1;

		for (int i = 0; i < taskListModel.getSize(); i++) {
			if (taskListModel.getElementAt(i).equals(task)) index = i;
		}

		if (index != -1) {
			taskListModel.remove(index);
		}

		repaint();
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < taskListModel.getSize(); i++) {
			tasks.add(taskListModel.getElementAt(i));
		}

		return tasks;
	}

	public void clearView() {
		taskListModel.removeAllElements();
	}
}

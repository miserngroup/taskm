package pl.misern.taskm.gui.screen;

import lombok.Getter;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.event.command.task.AssignTaskCommand;
import pl.misern.taskm.model.EntryActionType;
import pl.misern.taskm.event.command.dialog.ShowEntryDialogCommand;
import pl.misern.taskm.gui.widget.TaskList;
import pl.misern.taskm.model.Task;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class TaskScreen extends JPanel {

	private final TaskList taskList = new TaskList();

	private final JButton addTask = new JButton("buttons.task.add");
	private final JButton assignTask = new JButton("buttons.task.assign");
	private final JButton removeTask = new JButton("buttons.task.remove");

	public TaskScreen() {
		setBorder(new TitledBorder("view.task.title"));
		setLayout(new BorderLayout());
		setButtonsLook();
		setBehaviour();

		add(taskList, BorderLayout.CENTER);
		add(createViewPanel(), BorderLayout.SOUTH);
	}

	private void setButtonsLook() {
		assignTask.setEnabled(false);
		assignTask.setContentAreaFilled(false);
		removeTask.setEnabled(false);
		removeTask.setContentAreaFilled(false);
	}

	private void setBehaviour() {
		taskList.addListSelectionListener(action ->
				switchButtonEnabled());

		removeTask.addActionListener(action ->
				removeTask(taskList.getSelectedIndex()));

		addTask.addActionListener(action ->
				EventHandler.pushEvent(new ShowEntryDialogCommand(new Task(), EntryActionType.SAVE)));

		assignTask.addActionListener(action ->
				EventHandler.pushEvent(new AssignTaskCommand(getTaskList().getSelectedTask())));
	}

	private JPanel createViewPanel() {
		JPanel personButtonPanel = new JPanel(new GridLayout(1, 3));
		personButtonPanel.add(addTask);
		personButtonPanel.add(assignTask);
		personButtonPanel.add(removeTask);

		return personButtonPanel;
	}

	private void switchButtonEnabled() {
		assignTask.setEnabled(taskList.getSelectedIndex() >= 0);
		removeTask.setEnabled(taskList.getSelectedIndex() >= 0);
	}

	public void addTask(Task task) {
		taskList.addTask(task);
	}

	public void updateTask(Task task) {
		taskList.updateTask(task);
	}

	public void removeTask(int index) {
		taskList.removeTask(index);
	}

	public void removeTask(Task task) {
		taskList.removeTask(task);
	}

	public List<Task> getTasks() {
		return taskList.getTasks();
	}

	public void clearView() {
		taskList.removeAllElements();
	}

	public void changeLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);

		setBorder(BorderFactory.createTitledBorder(bundle.getString("view.task.title")));
		addTask.setText(bundle.getString("buttons.add"));
		assignTask.setText(bundle.getString("buttons.assign"));
		removeTask.setText(bundle.getString("buttons.remove"));
	}
}

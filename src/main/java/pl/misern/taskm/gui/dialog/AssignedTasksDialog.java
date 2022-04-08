package pl.misern.taskm.gui.dialog;

import lombok.Getter;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class AssignedTasksDialog extends JDialog {

	private final Person person;

	private final JList<Task> taskList;
	private final DefaultListModel<Task> taskListModel;

	private final JButton finishButton = new JButton("buttons.finish");
	private final JButton removeButton = new JButton("buttons.remove");
	private final JButton exitButton = new JButton("buttons.exit");

	private Task actualTask = new Task();

	public AssignedTasksDialog(Person person) {
		super(Window.getInstance(), true);
		this.person = person;
		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);

		setLook();
		setTaskList();
		loadTasks();
		setLanguage(Window.getInstance().getLocale());

		add(taskList, BorderLayout.CENTER);
		add(createViewPanel(), BorderLayout.SOUTH);
	}

	private void setLook() {
		setSize(new Dimension(400, 300));
		setModalityType(ModalityType.MODELESS);
		setLocationRelativeTo(Window.getInstance());
		setTitle("dialog.title.tasks " + person.getName());
		setLayout(new BorderLayout());
		finishButton.setEnabled(false);
		removeButton.setEnabled(false);
	}

	private void setTaskList() {
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(action -> {
			finishButton.setEnabled(taskList.getSelectedIndex() >= 0);
			removeButton.setEnabled(taskList.getSelectedIndex() >= 0);
			actualTask = taskList.getSelectedValue();
		});
	}

	private JPanel createViewPanel() {
		JPanel listButtonPanel = new JPanel(new GridLayout(1, 3));
		listButtonPanel.add(finishButton);
		listButtonPanel.add(removeButton);
		listButtonPanel.add(exitButton);

		return listButtonPanel;
	}

	public void loadTasks() {
		taskListModel.removeAllElements();
		for (Task task : person.getTasks()) {
			taskListModel.addElement(task);
		}
	}

	public void setLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
		finishButton.setText(bundle.getString("buttons.finish"));
		removeButton.setText(bundle.getString("buttons.remove"));
		exitButton.setText(bundle.getString("buttons.exit"));
		setTitle(bundle.getString("dialog.title.tasks").replace("%s", person.getName()));
	}
}

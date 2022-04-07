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

@Getter
public class AssignedTasksDialog extends JDialog {

	private final Person person;

	private final JList<Task> taskList;
	private final DefaultListModel<Task> taskListModel;

	private final JButton finishButton = new JButton("Zakończ");
	private final JButton removeButton = new JButton("Usuń");
	private final JButton exitButton = new JButton("Wyjdź");

	private Task actualTask = new Task();

	public AssignedTasksDialog(Person person) {
		super(Window.getInstance(), true);
		this.person = person;

		setSize(new Dimension(400, 300));
		setModalityType(ModalityType.MODELESS);
		setLocationRelativeTo(Window.getInstance());
		setTitle("Zadania " + person.getName());
		setLayout(new BorderLayout());

		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.addListSelectionListener(action -> {
			removeButton.setEnabled(taskList.getSelectedIndex() >= 0);
			actualTask = taskList.getSelectedValue();
		});

		loadTasks();

		JPanel listButtonPanel = new JPanel(new GridLayout(1, 3));
		listButtonPanel.add(finishButton);
		listButtonPanel.add(removeButton);
		listButtonPanel.add(exitButton);

		add(taskList, BorderLayout.CENTER);
		add(listButtonPanel, BorderLayout.SOUTH);
	}

	public void loadTasks() {
		taskListModel.removeAllElements();
		for (Task task : person.getTasks()) {
			taskListModel.addElement(task);
		}
	}
}

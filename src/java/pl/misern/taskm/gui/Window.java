package pl.misern.taskm.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pl.misern.taskm.gui.dialog.AssignedTasksDialog;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.gui.dialog.EntryDialogFactory;
import pl.misern.taskm.gui.menu.ApplicationMenuBar;
import pl.misern.taskm.gui.screen.MainScreen;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.SaveState;
import pl.misern.taskm.model.Task;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

@Getter
public class Window extends JFrame {

	private static Window instance;
	private final MainScreen mainScreen = new MainScreen();
	private final ApplicationMenuBar applicationMenuBar = new ApplicationMenuBar();
	private EntryDialog<?> dialog;
	private AssignedTasksDialog tasksDialog;

	@Setter
	private File saveState;

	private boolean isSaved = false;

	private Window() {
		setLook();
		setComponents();
		setBehaviour();
		setVisible(true);
	}

	@SneakyThrows
	private void setLook() {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setSize(new Dimension(1024, 768));
		setLocation(calculateScreenCenter());
		setJMenuBar(applicationMenuBar);
		setLayout(new BorderLayout());
		setTitle("Task Manager — Nowy plik");
	}

	private Point calculateScreenCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;

		return new Point(x, y);
	}

	private void setComponents() {
		add(mainScreen, BorderLayout.CENTER);
	}

	private void setBehaviour() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(windowClosing());
	}

	private WindowListener windowClosing() {
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!isSaved) {
					int confirm = JOptionPane.showOptionDialog(
							null, "Czy na pewno chcesz zamknąć?",
							"Exit Confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						dispose();
						System.exit(0);
					}
				}

			}
		};
	}

	public static synchronized Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

	public void showEntryDialog(Entity entity) {
		dialog = EntryDialogFactory.getInstance(entity);
		dialog.setVisible(true);
	}

	public void showTaskListDialog(Person person) {
		tasksDialog = new AssignedTasksDialog(person);
		tasksDialog.setVisible(true);
	}

	public SaveState generateSaveState() {
		List<Person> team = getMainScreen().getTeamScreen().getTeam();
		List<Task> tasks = getMainScreen().getTaskScreen().getTasks();

		return new SaveState(team, tasks);
	}

	public void loadSaveState(SaveState saveState) {
		resetState();
		saveState.getTeam().forEach(person -> getMainScreen().getTeamScreen().addPerson(person));
		saveState.getTasks().forEach(task -> getMainScreen().getTaskScreen().addTask(task));
	}

	public void resetState() {
		getMainScreen().getTeamScreen().clearView();
		getMainScreen().getTaskScreen().clearView();
	}

	public void setSaved(boolean saved) {
		this.isSaved = saved;

		if (!isSaved && !getTitle().endsWith("*"))
			setTitle(getTitle() + " *");
	}
}

package pl.misern.taskm.gui;

import lombok.Getter;
import lombok.SneakyThrows;

import pl.misern.taskm.event.listener.WindowClosingAdapter;
import pl.misern.taskm.gui.dialog.AssignedTasksDialog;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.service.EntryDialogFactory;
import pl.misern.taskm.gui.menu.ApplicationMenuBar;
import pl.misern.taskm.gui.screen.MainScreen;
import pl.misern.taskm.model.ApplicationState;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.SaveState;
import pl.misern.taskm.model.Task;
import pl.misern.taskm.service.MathUtils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class Window extends JFrame {

	private static Window instance;

	private final MainScreen mainScreen = new MainScreen();
	private final ApplicationMenuBar applicationMenuBar = new ApplicationMenuBar();

	private EntryDialog<?> entryDialog;
	private AssignedTasksDialog assignedTasksDialog;

	private final transient SaveState saveState = new SaveState();

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
		setLocation(MathUtils.calculateScreenCenter(getWidth(), getHeight()));
		setJMenuBar(applicationMenuBar);
		setLayout(new BorderLayout());
		setIcon();
	}

	private void setIcon() {
		URL iconURL = Window.class.getResource("/icon.png");
		assert iconURL != null;
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
	}

	private void setComponents() {
		add(mainScreen, BorderLayout.CENTER);
	}

	private void setBehaviour() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowClosingAdapter(saveState));
	}

	public void showEntryDialog(Entity entity) {
		entryDialog = EntryDialogFactory.getInstance(entity);
		entryDialog.setVisible(true);
	}

	public void showTaskListDialog(Person person) {
		assignedTasksDialog = new AssignedTasksDialog(person);
		assignedTasksDialog.setVisible(true);
	}

	public ApplicationState generateSaveState() {
		List<Person> team = getMainScreen().getTeamScreen().getTeam();
		List<Task> tasks = getMainScreen().getTaskScreen().getTasks();

		return new ApplicationState(team, tasks);
	}

	public void loadSaveState(ApplicationState applicationState) {
		saveState.setApplicationState(applicationState);
		resetState();
		loadTeam(applicationState);
		loadTasks(applicationState);
	}

	public void resetState() {
		getMainScreen().getTeamScreen().clearView();
		getMainScreen().getTaskScreen().clearView();
	}

	private void loadTeam(ApplicationState applicationState) {
		applicationState.team().forEach(person -> getMainScreen().getTeamScreen().addPerson(person));
	}

	private void loadTasks(ApplicationState applicationState) {
		applicationState.tasks().forEach(task -> getMainScreen().getTaskScreen().addTask(task));
	}

	public void setSaved(boolean saved) {
		saveState.setSaved(saved);
		if (!saveState.isSaved() && !getTitle().endsWith("*"))
			setTitle(getTitle() + " *");
	}

	public void changeLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
		setTitle(bundle.getString("title.name"));
		mainScreen.changeLanguage(currentLocale);
		applicationMenuBar.changeLanguage(currentLocale);
		setLocale(currentLocale);
	}

	public static synchronized Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}
}

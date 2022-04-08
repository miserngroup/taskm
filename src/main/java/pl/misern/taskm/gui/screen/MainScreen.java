package pl.misern.taskm.gui.screen;

import lombok.Getter;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.util.Locale;

@Getter
public class MainScreen extends JPanel {

	private final TeamScreen teamScreen = new TeamScreen();
	private final TaskScreen taskScreen = new TaskScreen();

	public MainScreen() {
		setLayout(new BorderLayout());

		JSplitPane splitter = new JSplitPane();
		splitter.setContinuousLayout(true);
		splitter.setResizeWeight(0.5);
		splitter.setEnabled(false);

		splitter.setLeftComponent(teamScreen);
		splitter.setRightComponent(taskScreen);

		add(splitter, BorderLayout.CENTER);
	}

	public void changeLanguage(Locale currentLocale) {
		teamScreen.changeLanguage(currentLocale);
		taskScreen.changeLanguage(currentLocale);
	}
}

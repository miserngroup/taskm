package pl.misern.taskm.gui.screen;

import lombok.Getter;
import pl.misern.taskm.event.EventHandler;
import pl.misern.taskm.model.EntryActionType;
import pl.misern.taskm.event.command.dialog.ShowEntryDialogCommand;
import pl.misern.taskm.event.command.dialog.ShowTaskListDialogCommand;
import pl.misern.taskm.gui.widget.PersonList;
import pl.misern.taskm.model.Person;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class TeamScreen extends JPanel {

	private final PersonList peopleList = new PersonList();

	private final JButton addPerson = new JButton("buttons.add");
	private final JButton updatePerson = new JButton("buttons.update");
	private final JButton removePerson = new JButton("buttons.remove");

	public TeamScreen() {
		setBorder(new TitledBorder("view.team.title"));
		setLayout(new BorderLayout());
		setButtonsLook();
		setBehaviour();

		add(peopleList, BorderLayout.CENTER);
		add(createViewPanel(), BorderLayout.SOUTH);
	}

	private void setButtonsLook() {
		updatePerson.setEnabled(false);
		updatePerson.setContentAreaFilled(false);
		removePerson.setEnabled(false);
		removePerson.setContentAreaFilled(false);
	}

	private void setBehaviour() {
		peopleList.addListSelectionListener(action ->
				switchButtonEnabled());

		removePerson.addActionListener(action ->
				removePerson(peopleList.getSelectedValue()));

		getAddPerson().addActionListener(action ->
				EventHandler.pushEvent(new ShowEntryDialogCommand(new Person(), EntryActionType.SAVE)));

		getUpdatePerson().addActionListener(action ->
				EventHandler.pushEvent(new ShowEntryDialogCommand(peopleList.getSelectedPerson(), EntryActionType.EDIT)));

		peopleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = peopleList.getSelectedIndex();
					EventHandler.pushEvent(new ShowTaskListDialogCommand(index));
				}
			}
		});
	}

	private JPanel createViewPanel() {
		JPanel teamButtonPanel = new JPanel(new GridLayout(1, 3));
		teamButtonPanel.add(addPerson);
		teamButtonPanel.add(updatePerson);
		teamButtonPanel.add(removePerson);

		return teamButtonPanel;
	}

	private void switchButtonEnabled() {
		updatePerson.setEnabled(peopleList.getSelectedIndex() >= 0);
		removePerson.setEnabled(peopleList.getSelectedIndex() >= 0);
	}

	public void addPerson(Person person) {
		peopleList.addPerson(person);
	}

	public void updatePerson(Person person) {
		peopleList.updatePerson(person);
	}

	public void removePerson(Person person) {
		peopleList.removePerson(person);
	}

	public Person getPersonAt(int index) {
		return peopleList.getPeople().get(index);
	}

	public List<Person> getTeam() {
		return peopleList.getPeople();
	}

	public void clearView() {
		peopleList.removeAllElements();
	}

	public void changeLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);

		setBorder(BorderFactory.createTitledBorder(bundle.getString("view.team.title")));
		addPerson.setText(bundle.getString("buttons.add"));
		updatePerson.setText(bundle.getString("buttons.update"));
		removePerson.setText(bundle.getString("buttons.remove"));
	}
}

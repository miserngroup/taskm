package pl.misern.taskm.gui.screen;

import lombok.Getter;
import pl.misern.taskm.model.Person;

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
public class TeamScreen extends JPanel {

	private final JList<Person> teamList;
	private final DefaultListModel<Person> teamListModel;

	private final JButton addPerson = new JButton("Dodaj");
	private final JButton updatePerson = new JButton("Edytuj");
	private final JButton removePerson = new JButton("Usuń");

	private Person actualPerson = new Person();

	public TeamScreen() {
		setBorder(new TitledBorder("Zespół"));
		setLayout(new BorderLayout());

		teamListModel = new DefaultListModel<>();
		teamList = new JList<>(teamListModel);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamList.addListSelectionListener(action -> {
			if (teamList.getSelectedIndex() >= 0) {
				actualPerson = teamList.getSelectedValue();
				updatePerson.setEnabled(true);
				removePerson.setEnabled(true);
			} else {
				updatePerson.setEnabled(false);
				removePerson.setEnabled(false);
			}
		});

		updatePerson.setEnabled(false);
		updatePerson.setContentAreaFilled(false);

		removePerson.setEnabled(false);
		removePerson.setContentAreaFilled(false);
		removePerson.addActionListener(action -> {
			Person selectedValue = teamList.getSelectedValue();
			removePerson(selectedValue);
		});

		JPanel teamButtonPanel = new JPanel(new GridLayout(1, 3));
		teamButtonPanel.add(addPerson);
		teamButtonPanel.add(updatePerson);
		teamButtonPanel.add(removePerson);

		add(teamList, BorderLayout.CENTER);
		add(teamButtonPanel, BorderLayout.SOUTH);
	}

	public void addPerson(Person person) {
		teamListModel.addElement(person);
		repaint();
	}

	public void updatePerson(Person person) {
		actualPerson.setName(person.getName());
		repaint();
	}

	public void removePerson(Person person) {
		int index = -1;

		for (int i = 0; i < teamListModel.getSize(); i++) {
			if (teamListModel.getElementAt(i).equals(person)) index = i;
		}

		if (index != -1) {
			teamListModel.remove(index);
		}
		repaint();
	}

	public Person getPersonAt(int index) {
		return teamListModel.getElementAt(index);
	}

	public List<Person> getTeam() {
		List<Person> team = new ArrayList<>();
		for (int i = 0; i < teamListModel.getSize(); i++) {
			team.add(teamListModel.getElementAt(i));
		}

		return team;
	}

	public void clearView() {
		teamListModel.removeAllElements();
	}
}

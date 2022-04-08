package pl.misern.taskm.gui.widget;

import lombok.Getter;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Person;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.util.ArrayList;
import java.util.List;

public class PersonList extends JList<Person> {

	private final DefaultListModel<Person> personListModel = new DefaultListModel<>();

	@Getter
	private Person selectedPerson = new Person();

	public PersonList() {
		setModel(personListModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addListSelectionListener(action ->
				selectedPerson = (getSelectedIndex() >= 0) ? getSelectedValue() : selectedPerson);
	}

	public void addPerson(Person person) {
		personListModel.addElement(person);
	}

	public void updatePerson(Person person) {
		selectedPerson.setName(person.getName());
	}

	public void removePerson(int index) {
		if (index != -1) {
			personListModel.remove(index);
			Window.getInstance().setSaved(false);
		}
	}

	public void removePerson(Person person) {
		int index = personListModel.indexOf(person);
		removePerson(index);
	}

	public List<Person> getPeople() {
		List<Person> people = new ArrayList<>();
		for (int i = 0; i < personListModel.getSize(); i++) {
			people.add(personListModel.getElementAt(i));
		}

		return people;
	}

	public void removeAllElements() {
		personListModel.removeAllElements();
	}
}

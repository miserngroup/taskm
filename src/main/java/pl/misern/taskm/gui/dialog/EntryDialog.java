package pl.misern.taskm.gui.dialog;

import lombok.Getter;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.service.EntityFactory;
import pl.misern.taskm.model.Person;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class EntryDialog<T extends Entity> extends JDialog {

	private final transient T entry;

	private final JButton saveButton = new JButton("buttons.save");
	private final JButton exitButton = new JButton("buttons.exit");
	private final JTextField nameField = new JTextField();
	private final JPanel nameFieldPanel = new JPanel(new BorderLayout());

	public EntryDialog(T entry) {
		super(Window.getInstance(), true);
		this.entry = entry;

		setLook();
		setLanguage(Window.getInstance().getLocale());
		add(createContentPanel());
	}

	private void setLook() {
		setSize(new Dimension(400, 300));
		setModalityType(ModalityType.MODELESS);
		setLocationRelativeTo(Window.getInstance());
		setTitle(addTitle());
	}

	private JPanel createContentPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 4;
		gbc.weighty = 4;
		gbc.ipady = 16;
		gbc.gridx = 0;
		gbc.gridy = 0;

		contentPanel.add(createNamePanel(), gbc);
		gbc.gridy++;
		gbc.ipady = 0;
		gbc.gridheight = 1;

		contentPanel.add(createSaveButtonPanel(), gbc);
		gbc.gridy++;

		contentPanel.add(createExitButtonPanel(), gbc);

		return contentPanel;
	}

	private String addTitle() {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Window.getInstance().getLocale());
		if (entry != null && entry.getName() != null)
			return entry.getName();
		else
			return bundle.getString("dialog.title.entry");
	}

	private JPanel createNamePanel() {
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);
		Border innerBorder = BorderFactory.createTitledBorder(createNamePanelTitle());

		nameField.setText(entry.getName());
		nameFieldPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		nameFieldPanel.add(nameField, BorderLayout.CENTER);

		return nameFieldPanel;
	}

	private String createNamePanelTitle() {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Window.getInstance().getLocale());
		if (entry instanceof Person)
			return bundle.getString("fields.person.data");
		else
			return bundle.getString("fields.name");
	}

	private JPanel createSaveButtonPanel() {
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);

		JPanel saveButtonField = new JPanel(new BorderLayout());
		saveButtonField.setBorder(outerBorder);
		saveButtonField.add(saveButton, BorderLayout.SOUTH);

		return saveButtonField;
	}

	private JPanel createExitButtonPanel() {
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);

		JPanel exitButtonField = new JPanel(new BorderLayout());
		exitButtonField.setBorder(outerBorder);
		exitButtonField.add(exitButton, BorderLayout.SOUTH);

		return exitButtonField;
	}

	public Entity prepareEntity() {
		Entity entity = EntityFactory.createInstance(entry);
		entity.setName(nameField.getText());
		return entity;
	}

	public void setLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
		saveButton.setText(bundle.getString("buttons.save"));
		exitButton.setText(bundle.getString("buttons.exit"));
		setTitle(addTitle());
	}
}

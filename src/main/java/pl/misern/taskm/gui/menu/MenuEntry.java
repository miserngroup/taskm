package pl.misern.taskm.gui.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JComponent;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@NoArgsConstructor
@Getter
@Setter
public class MenuEntry extends JMenu {

	private String key;

	public MenuEntry(String key) {
		this.key = key;
		setText(key);
	}

	public void changeLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
		setText(bundle.getString(key));
		getChildren().forEach(item -> {
			if (item instanceof MenuEntry menuEntry) {
				menuEntry.changeLanguage(currentLocale);
			} else if (item instanceof MenuItem menuItemEntry) {
				menuItemEntry.changeLanguage(currentLocale);
			}
		});
	}

	public List<JComponent> getChildren() {
		List<JComponent> children = new ArrayList<>();
		if (getItemCount() != 0) {
			for (int i = 0; i < getItemCount(); i++) {
				children.add(getItem(i));
			}
		}

		return children;
	}
}

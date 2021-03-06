package pl.misern.taskm.gui.menu;

import lombok.Getter;
import lombok.Setter;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
@Setter
public class MenuItem extends JMenuItem {

	private String key;

	public MenuItem(String key) {
		this.key = key;
		setText(key);
	}

	public MenuItem(String key, char keyCode) {
		this.key = key;
		setAccelerator(KeyStroke.getKeyStroke(keyCode, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		setText(key);
	}

	public void changeLanguage(Locale currentLocale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
		setText(bundle.getString(key));
	}
}

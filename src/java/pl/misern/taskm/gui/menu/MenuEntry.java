package pl.misern.taskm.gui.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JMenu;

@NoArgsConstructor
@Getter
@Setter
public class MenuEntry extends JMenu {

	private String key;

	public MenuEntry(String key) {
		this.key = key;
		setText(key);
	}
}

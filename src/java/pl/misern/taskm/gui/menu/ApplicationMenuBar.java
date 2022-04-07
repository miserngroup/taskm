package pl.misern.taskm.gui.menu;

import lombok.Getter;

import javax.swing.JMenuBar;
import java.util.EnumMap;
import java.util.Map;

@Getter
public class ApplicationMenuBar extends JMenuBar {

	private final Map<MenuCategory, MenuEntry> menus = new EnumMap<>(MenuCategory.class);

	public ApplicationMenuBar() {
		init();
		createFileMenu();
	}

	private void init() {
		menus.put(MenuCategory.FILE, new MenuEntry("Plik"));
		menus.put(MenuCategory.HELP, new MenuEntry("Pomoc"));
		menus.values().forEach(this::add);
	}

	private void createFileMenu() {
		menus.get(MenuCategory.FILE).add(new MenuItem("Nowy", 'N'));
		menus.get(MenuCategory.FILE).add(new MenuItem("Wczytaj", 'O'));
		menus.get(MenuCategory.FILE).add(new MenuItem("Zapisz", 'S'));
		menus.get(MenuCategory.FILE).add(new MenuItem("Wyjdź"));
	}
}

package pl.misern.taskm.gui.menu;

import lombok.Getter;

import javax.swing.JMenuBar;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

@Getter
public class ApplicationMenuBar extends JMenuBar {

	private final Map<MenuCategory, MenuEntry> menus = new EnumMap<>(MenuCategory.class);

	public ApplicationMenuBar() {
		init();
		createFileMenu();
	}

	private void init() {
		menus.put(MenuCategory.FILE, new MenuEntry("menu.title.file"));
		menus.values().forEach(this::add);
	}

	private void createFileMenu() {
		menus.get(MenuCategory.FILE).add(new MenuItem("menu.file.new", 'N'));
		menus.get(MenuCategory.FILE).add(new MenuItem("menu.file.open", 'O'));
		menus.get(MenuCategory.FILE).add(new MenuItem("menu.file.save", 'S'));
		menus.get(MenuCategory.FILE).add(new MenuItem("menu.file.exit"));
	}

	public void changeLanguage(Locale currentLocale) {
		menus.values().forEach(menu -> menu.changeLanguage(currentLocale));
		menus.values().forEach(menuItem -> menuItem.getChildren().forEach(item -> {
			if (item instanceof MenuEntry menuEntry) {
				menuEntry.changeLanguage(currentLocale);
			} else if (item instanceof MenuItem menuItemEntry) {
				menuItemEntry.changeLanguage(currentLocale);
			}
		}));
	}
}

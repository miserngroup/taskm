package pl.misern.taskm.gui.menu;

import lombok.Getter;

@Getter
public enum MenuCategory {
	FILE("Plik"),
	HELP("Pomoc");

	private final String label;

	MenuCategory(String label) {
		this.label = label;
	}
}

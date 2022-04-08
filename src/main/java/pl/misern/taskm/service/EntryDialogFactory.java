package pl.misern.taskm.service;

import lombok.SneakyThrows;
import pl.misern.taskm.exception.EntityInstanceException;
import pl.misern.taskm.gui.dialog.EntryDialog;
import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

public class EntryDialogFactory {

	private EntryDialogFactory() {}

	@SneakyThrows
	public static EntryDialog<Entity> getInstance(Entity entity) {
		if (entity instanceof Person person) {
			return new EntryDialog<>(person);
		} else if (entity instanceof Task task) {
			return new EntryDialog<>(task);
		}

		throw new EntityInstanceException(entity);
	}
}

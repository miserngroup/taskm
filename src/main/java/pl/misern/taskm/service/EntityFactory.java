package pl.misern.taskm.service;

import pl.misern.taskm.model.Entity;
import pl.misern.taskm.model.Person;
import pl.misern.taskm.model.Task;

public class EntityFactory {

	private EntityFactory() {}

	public static <T extends Entity> Entity createInstance(T entry) {
		if (entry instanceof Person) {
			return new Person();
		} else {
			return new Task();
		}
	}
}

package pl.misern.taskm.model;

public class EntityFactory {
	public static <T extends Entity> Entity createInstance(T entry) {
		if (entry instanceof Person) {
			return new Person();
		} else {
			return new Task();
		}
	}
}

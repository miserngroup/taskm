package pl.misern.taskm.exception;

import pl.misern.taskm.model.Entity;

public class EntityInstanceException extends RuntimeException {
	public EntityInstanceException(Entity entity) {
		super("Unrecognized entity type " + entity.getClass().getName());
	}
}

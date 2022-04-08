package pl.misern.taskm.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Entity implements Serializable {
	protected String name;

	@Override
	public String toString() {
		return name;
	}
}

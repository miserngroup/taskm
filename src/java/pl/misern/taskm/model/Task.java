package pl.misern.taskm.model;

import lombok.Data;

@Data
public class Task implements Entity {
	private String name;

	@Override
	public String toString() {
		return name;
	}
}

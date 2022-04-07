package pl.misern.taskm.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person implements Entity {
	private String name;
	private List<Task> tasks = new ArrayList<>();

	@Override
	public String toString() {
		return name;
	}
}

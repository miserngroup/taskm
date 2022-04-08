package pl.misern.taskm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Person extends Entity {
	private List<Task> tasks = new ArrayList<>();

	@Override
	public String toString() {
		return super.toString();
	}
}

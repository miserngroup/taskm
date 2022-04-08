package pl.misern.taskm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Task extends Entity {
	@Override
	public String toString() {
		return super.toString();
	}
}

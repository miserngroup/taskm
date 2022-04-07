package pl.misern.taskm.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class SaveState implements Serializable {
	private final List<Person> team;
	private final List<Task> tasks;
}

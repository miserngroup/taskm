package pl.misern.taskm.model;


import java.io.Serializable;
import java.util.List;

public record ApplicationState(List<Person> team,
						List<Task> tasks) implements Serializable {
}

package pl.misern.taskm.model;

import java.io.Serializable;

public interface Entity extends Serializable {
	String getName();
	void setName(String name);
}

package pl.misern.taskm.model;

import lombok.Data;

import java.io.File;

@Data
public class SaveState {
	private ApplicationState applicationState;
	private File saveFile;
	private boolean isSaved = false;
}

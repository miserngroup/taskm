package pl.misern.taskm.event.command.save;

import lombok.SneakyThrows;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.ApplicationState;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadFileStateCommand implements Command {
	@Override
	@SneakyThrows
	public void execute() {
		Window window = Window.getInstance();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setFileFilter(new FileNameExtensionFilter("Binary file", "bin"));

		if (fileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
			File fileToLoad = fileChooser.getSelectedFile();
			loadSerialized(fileToLoad);
			window.getSaveState().setSaveFile(fileToLoad);
			window.setTitle("Task Manager — " + fileToLoad.getName());
		}
	}

	@SneakyThrows
	private void loadSerialized(File file) {
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		ApplicationState applicationState = (ApplicationState) in.readObject();
		Window.getInstance().loadSaveState(applicationState);
	}

}

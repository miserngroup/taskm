package pl.misern.taskm.event.command;

import lombok.SneakyThrows;
import pl.misern.taskm.event.command.Command;
import pl.misern.taskm.gui.Window;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFileStateCommand implements Command {
	@Override
	@SneakyThrows
	public void execute() {
		Window window = Window.getInstance();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setFileFilter(new FileNameExtensionFilter("Binary file", "bin"));

		File fileToSave = null;
		if (window.getSaveState() != null) {
			fileToSave = window.getSaveState();
		} else {
			if (fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
				fileToSave = fileChooser.getSelectedFile();
				FileFilter fileFilter = fileChooser.getFileFilter();
				if (fileFilter instanceof FileNameExtensionFilter fileNameExtensionFilter && ! fileFilter.accept(fileToSave)) {
					String extension = fileNameExtensionFilter.getExtensions()[0];
					String newName = fileToSave.getName() + "." + extension;
					fileToSave = new File(fileToSave.getParent(), newName);
				}
			}
		}

		if (fileToSave != null) {
			saveSerialized(fileToSave);
			window.setSaveState(fileToSave);
			window.setSaved(true);
			window.setTitle("Task Manager — " + fileToSave.getName());
		}
	}

	@SneakyThrows
	public static void saveSerialized(File file) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(Window.getInstance().generateSaveState());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

package pl.misern.taskm.event.listener;

import lombok.RequiredArgsConstructor;
import pl.misern.taskm.gui.Window;
import pl.misern.taskm.model.SaveState;

import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class WindowClosingAdapter extends WindowAdapter {

	private final SaveState saveFile;

	@Override
	public void windowClosing(WindowEvent e) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Window.getInstance().getLocale());

		if (!saveFile.isSaved()) {
			int confirm = JOptionPane.showOptionDialog(
					null, bundle.getString("dialog.confirm.exit"),
					bundle.getString("buttons.exit"), JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				Window.getInstance().dispose();
				System.exit(0);
			}
		}
	}
}

package pl.misern.taskm.service;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class MathUtils {

	private MathUtils() {}

	public static Point calculateScreenCenter(int windowWidth, int windowHeight) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - windowWidth) / 2;
		int y = (screenSize.height - windowHeight) / 2;

		return new Point(x, y);
	}
}

package itemresearcher.fontloader;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * @author Robert G
 *
 */
public class FontLoader {
	
	private final String filePath = "/itemresearcher/fontloader/fonts/";

	/**
	 * Loads the specified font with the specified type, style and size.
	 * @param fontName
	 * @param type
	 * @param style
	 * @param size
	 * @return the specified font if found otherwise Segoe UI Semibold with the specified style and size.
	 */
	public Font loadFont(String fontName, int type, int style, int size) {
		try {
			Font font = Font.createFont(type, this.getClass().getResourceAsStream(filePath + fontName));
			font = font.deriveFont(style, size);
			final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Font("Segoe UI Semibold", style, size);
	}
	
}

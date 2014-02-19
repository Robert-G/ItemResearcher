package itemresearcher.fontloader;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * @author Robert G
 *
 */
public class FontLoader {

	/**
	 * 
	 * @param url
	 * @param type
	 * @param style
	 * @param size
	 * @return
	 */
	public Font loadFont(String fontName, int type, int style, int size) {
		Font font = null;
		try {
			font = Font.createFont(type, this.getClass().getResourceAsStream("/itemresearcher/fontloader/fonts/" + fontName));
			font = font.deriveFont(style, size);
			final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
}

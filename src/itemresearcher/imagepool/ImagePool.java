package itemresearcher.imagepool;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImagePool {
	
	private HashMap<String, Image> imageCache = new HashMap<>();

	public Image getImage(String url) {
		if (!imageCache.containsKey(url)) {
			return loadImage(url);
		}
		return imageCache.get(url);
	}
	
	private Image loadImage(String url) {
		Image image = null;
		try {
			image = ImageIO.read(new URL(url));
			if (image != null) {
				imageCache.put(url, image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}

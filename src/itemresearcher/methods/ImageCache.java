package itemresearcher.methods;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * @author Robert G
 *
 */
public class ImageCache {
	
	private HashMap<String, Image> imageCache = new HashMap<>();

	/**
	 * Loads an image from the specified url, if the image is not null it is cached.
	 * @param url the url of the image.
	 * @return an image loaded from the specified url, null if no image found.
	 */
	public Image getImage(String url) {
		if (!imageCache.containsKey(url)) {
			return loadImage(url);
		}
		return imageCache.get(url);
	}
	
	/***
	 * Loads an image from the specified url and saves it in the imageCache.
	 * @param url
	 * @return an image loaded from the specified url, null if no image was loaded.
	 */
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

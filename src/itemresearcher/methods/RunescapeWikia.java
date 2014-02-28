package itemresearcher.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Robert G
 *
 */
public class RunescapeWikia {
	
	private final String runescape_wikia_url = "http://runescape.wikia.com/wiki/Exchange:";
	private final String regex = "<ul><li><b><a href=\"/wiki/Grand_Exchange_Database\" title=\"Grand Exchange Database\">Exchange ID</a>:</b> <span id=\"GEDBID\">";
	private final Map<String, Integer> id_cache = new HashMap<>();
	private final ArrayList<String> history = new ArrayList<>();
	
	/**
	 * Loads the id of the item with the specified name.
	 * 
	 * @param itemName the name of the item
	 * @return the id of the item, -1 if item not found.
	 */
	public int getItemId(String itemName) {
		int id = -1;
		if (itemName != null && !itemName.isEmpty()) {
			if (id_cache.get(itemName) != null) {
				return id_cache.get(itemName).intValue();
			} else {
				if (!history.contains(itemName)) {
					history.add(itemName);
					final String url = runescape_wikia_url + itemName.replaceAll(" ", "_");
					final String loadedText = IO.loadText(url, regex);
					if (loadedText != null && !loadedText.isEmpty() && loadedText.contains(regex)) {
						try {
							id = Integer.parseInt(loadedText.replaceAll("\\D", ""));
							id_cache.put(itemName, id);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return id;
	}

}

package itemresearcher.grandexchange;

import itemresearcher.geitem.GEItem;
import itemresearcher.io.IO;
import itemresearcher.runescapewikia.RunescapeWikia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Robert G
 *
 */
public class GrandExchange {
	
	private static final String grandExchangeUrl = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";
	private static final String frontpageUrl = "http://services.runescape.com/m=itemdb_rs/frontpage.ws";
	private static final Map<Integer, GEItem> cachedItems = new HashMap<>();
	private static final List<Integer> checkedItems = new ArrayList<>();
	private static int itemOfTheWeek = -1;
	
	/**
	 * 
	 * @return the item of the week displayed on the grand exchange home page.
	 */
	public static GEItem getItemOfTheWeek() {
		if (itemOfTheWeek == -1) {
			itemOfTheWeek = Integer.parseInt(IO.loadText(frontpageUrl, "<a class=\"HoverText\" href=\"").replaceAll("\\D", ""));
		}
		return lookUpItem(itemOfTheWeek);
	}
	
	/**
	 * 
	 * @return the id of the item of the week
	 */
	public static int getItemOfTheWeekId() {
		return itemOfTheWeek;
	}
	
	/**
	 * 
	 * @param id the id of the item to look up.
	 * @return a GEItem containing the items price, name and id, null if item not found.
	 */
	public static GEItem lookUpItem(int id) {
		if (id > 0) {
			if (cachedItems.containsKey(id)) {
				return cachedItems.get(id);
			} else {
				if (!checkedItems.contains(id)) {
					checkedItems.add(id);
					final String info = IO.loadText(grandExchangeUrl + id);
					if (info != null) {
						final GEItem item = new GEItem(info);
						if (item != null) {
							cachedItems.put(id, item);
							return item;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param itemName the name of the item.
	 * @return a GEItem containing the items price, name and id, null if item not found.
	 */
	public static GEItem lookUpItem(String itemName) {
		final int id = RunescapeWikia.getItemId(itemName);
		return id != -1 ? lookUpItem(id) : null;
	}

}

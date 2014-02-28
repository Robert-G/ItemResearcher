package itemresearcher.methods;

import itemresearcher.io.IO;
import itemresearcher.methods.ItemCache.CacheItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Robert G
 *
 */
public class GrandExchange {
	
	public GrandExchange(MethodContext ctx) {
		this.ctx = ctx;
	}
	
	private final String grandExchangeUrl = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";
	private final String frontpageUrl = "http://services.runescape.com/m=itemdb_rs/frontpage.ws";
	private final Map<Integer, GEItem> cachedItems = new HashMap<>();
	private final List<Integer> checkedItems = new ArrayList<>();
	private final MethodContext ctx;
	private int itemOfTheWeek = -1;
	
	/**
	 * 
	 * @return the item of the week displayed on the grand exchange home page.
	 */
	public GEItem getItemOfTheWeek() {
		if (itemOfTheWeek == -1) {
			itemOfTheWeek = Integer.parseInt(IO.loadText(frontpageUrl, "<a class=\"HoverText\" href=\"").replaceAll("\\D", ""));
		}
		return lookUpItem(itemOfTheWeek);
	}
	
	/**
	 * 
	 * @return the id of the item of the week
	 */
	public int getItemOfTheWeekId() {
		return itemOfTheWeek;
	}
	
	/**
	 * Looks up the item with the specified id.
	 * Results are cached once loaded.
	 * @param id the id of the item to look up.
	 * @return a GEItem containing the items price, name and id, null if item not found.
	 */
	public GEItem lookUpItem(int id) {
		if (id > 0) {
			if (cachedItems.containsKey(id)) {
				return cachedItems.get(id);
			} else {
				if (!checkedItems.contains(id)) {
					checkedItems.add(id);
					final String info = IO.loadText(grandExchangeUrl + id);
					if (info != null && !info.isEmpty()) {
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
	 * Looks up the item with the specified name.
	 * Results are cached once loaded.
	 * @param itemName the name of the item.
	 * @return a GEItem containing the items price, name and id, null if item not found.
	 */
	public GEItem lookUpItem(String itemName) {
		final CacheItem item = ctx.itemCache.loadCacheItem(itemName);
		return item != null ? lookUpItem(item.getId()) : null;
	}
	
	public GEItem lookUpItem(Object object) {
		if (object instanceof String) {
			return lookUpItem((String)object);
		}
		return lookUpItem(((Integer)object).intValue());
	}
	
	public static class GEItem {
		
		private final String iconUrl;
		private final String largeIconUrl;
		private final int id;
		private final String type;
		private final String typeIconUrl;
		private final String name;
		private final String description;
		private final String currentPrice;
		
		private GEItem(String jsonArray) {
			this.iconUrl = Strings.getString(jsonArray, "\"icon\":\"", "\",\"icon_large\":\"");
			this.largeIconUrl = Strings.getString(jsonArray, "\"icon_large\":\"", "\",\"id\":");
			this.id = Integer.parseInt(Strings.getString(jsonArray, "\",\"id\":", ",\"type\":"));
			this.type = Strings.getString(jsonArray, "\"type\":\"", "\",\"typeIcon\":");
			this.typeIconUrl = Strings.getString(jsonArray, "\"typeIcon\":\"", "\",\"name\":");
			this.name = Strings.getString(jsonArray, "\"name\":\"", "\",\"description\":\"");
			this.description = Strings.getString(jsonArray, "\"description\":\"", "\",\"current\":");
			this.currentPrice = Strings.getString(jsonArray, "current\":{\"trend\":\"neutral\",\"price\":", "},\"today\":{").replaceAll("\"", "");
		}
		
		/**
		 * 
		 * @return the icon url of this item.
		 */
		public String getIconUrl() {
			return this.iconUrl;
		}
		
		/**
		 * 
		 * @return the large icon url of this item.
		 */
		public String getLargeIconUrl() {
			return this.largeIconUrl;
		}
		
		/**
		 * 
		 * @return the id of this item.
		 */
		public int getId() {
			return this.id;
		}
		
		/**
		 * 
		 * @return the type of this item.
		 */
		public String getType() {
			return this.type;
		}

		/**
		 * 
		 * @return the item type image url of this item.
		 */
		public String getTypeIconUrl() {
			return this.typeIconUrl;
		}

		/**
		 * 
		 * @return the name of this item.
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @return he description of this item.
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * 
		 * @return the current price of this item.
		 */
		public String getCurrentPrice() {
			return this.currentPrice;
		}

	}

}

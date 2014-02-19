package itemresearcher.geitem;

/**
 * @author Robert G
 *
 */
public class GEItem {
	
	private final String iconUrl;
	private final String largeIconUrl;
	private final int id;
	private final String type;
	private final String typeIconUrl;
	private final String name;
	private final String description;
	private final String currentPrice;
	
	public GEItem(String jsonArray) {
		this.iconUrl = getString(jsonArray, "\"icon\":\"", "\",\"icon_large\":\"");
		this.largeIconUrl = getString(jsonArray, "\"icon_large\":\"", "\",\"id\":");
		this.id = Integer.parseInt(getString(jsonArray, "\",\"id\":", ",\"type\":"));
		this.type = getString(jsonArray, "\"type\":\"", "\",\"typeIcon\":");
		this.typeIconUrl = getString(jsonArray, "\"typeIcon\":\"", "\",\"name\":");
		this.name = getString(jsonArray, "\"name\":\"", "\",\"description\":\"");
		this.description = getString(jsonArray, "\"description\":\"", "\",\"current\":");
		this.currentPrice = getString(jsonArray, "current\":{\"trend\":\"neutral\",\"price\":", "},\"today\":{").replaceAll("\"", "");
	}
	
	/**
	 * 
	 * @param startRegex
	 * @param endRegex
	 * @return
	 */
	private String getString(String jsonArray, String startRegex, String endRegex) {
		final int start = jsonArray.indexOf(startRegex) + startRegex.length();
		final int end = jsonArray.indexOf(endRegex);
		return jsonArray.substring(start, end);
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

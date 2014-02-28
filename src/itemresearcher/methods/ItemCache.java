package itemresearcher.methods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ItemCache {

	private final String filePath = "/itemresearcher/resources/itemList.txt";
	private final ArrayList<CacheItem> cache = new ArrayList<>();
	
	public ItemCache() {
		try {
			final InputStream in = this.getClass().getResourceAsStream(filePath);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				final String name = Strings.getString(line, "{\"Name\":\"", "\",\"");
				final int id = Integer.parseInt(Strings.getString(line, "\"Id\":", "}"));
				final CacheItem item = new CacheItem(name, id);
				cache.add(item);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<CacheItem> getCache() {
		return this.cache;
	}
	
	public CacheItem loadCacheItem(Object object) {
		for (CacheItem item : cache) {
			if (object instanceof String) {
				if (item.getName().equalsIgnoreCase((String)object)) {
					return item;
				}
			} else {
				if (item.getId() == ((Integer)object).intValue()) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static class CacheItem {
		
		private final String name;
		private final int id;
		
		private CacheItem(String name, int id) {
			this.name = name;
			this.id = id;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getId() {
			return this.id;
		}
		
	}

}

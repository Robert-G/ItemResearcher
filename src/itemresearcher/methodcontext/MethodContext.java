package itemresearcher.methodcontext;

import itemresearcher.fontloader.FontLoader;
import itemresearcher.grandexchange.GrandExchange;
import itemresearcher.imagepool.ImageCache;
import itemresearcher.runescapewikia.RunescapeWikia;

/**
 * @author Robert G
 *
 */
public class MethodContext {
	
	public final ImageCache imagePool;
	public final FontLoader fontLoader;
	public final GrandExchange grandExchange;
	public final RunescapeWikia runescapeWikia;

	public MethodContext() {
		this.imagePool = new ImageCache();
		this.fontLoader = new FontLoader();
		this.grandExchange = new GrandExchange(this);
		this.runescapeWikia = new RunescapeWikia();
	}

}

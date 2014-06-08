package itemresearcher.methods;


/**
 * @author Robert G
 *
 */
public class MethodContext {
	
	public final ImageCache imagePool;
	public final FontLoader fontLoader;
	public final GrandExchange grandExchange;
	public final ItemCache itemCache;
	public final RunescapeWikia wikia;

	public MethodContext() {
		this.imagePool = new ImageCache();
		this.fontLoader = new FontLoader();
		this.grandExchange = new GrandExchange(this);
		this.itemCache = new ItemCache();
		this.wikia = new RunescapeWikia();
	}

}

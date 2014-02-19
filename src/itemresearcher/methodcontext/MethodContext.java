package itemresearcher.methodcontext;

import itemresearcher.fontloader.FontLoader;
import itemresearcher.imagepool.ImagePool;

public class MethodContext {
	
	public final FontLoader fontLoader;
	public final ImagePool imagePool;

	public MethodContext() {
		this.imagePool = new ImagePool();
		this.fontLoader = new FontLoader();
	}

}

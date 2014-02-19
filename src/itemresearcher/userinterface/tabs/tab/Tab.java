package itemresearcher.userinterface.tabs.tab;

import itemresearcher.geitem.GEItem;
import itemresearcher.methods.MethodContext;
import itemresearcher.userinterface.UserInterface;

import java.awt.Font;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class Tab extends JPanel {
	
	private static final long serialVersionUID = -9123242609132488072L;
	protected final RenderingHints antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	protected final MethodContext ctx;
	protected final Font font1;
	protected final Font font2;
	protected GEItem item;
	
	public Tab(UserInterface parent) {
		super(null);
		this.ctx = parent.getContext();
		this.font1 = ctx.fontLoader.loadFont("Arabolical.ttf", Font.TRUETYPE_FONT, Font.PLAIN, 12);
		this.font2 = ctx.fontLoader.loadFont("Arabolical.ttf", Font.TRUETYPE_FONT, Font.BOLD, 16);
		this.setSize(520, 180);
	}

}

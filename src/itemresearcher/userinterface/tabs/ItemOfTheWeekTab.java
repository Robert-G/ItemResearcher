package itemresearcher.userinterface.tabs;

import itemresearcher.userinterface.UserInterface;
import itemresearcher.userinterface.tabs.tab.Tab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Robert G
 *
 */
public class ItemOfTheWeekTab extends Tab {

	private static final long serialVersionUID = 679957663265557761L;

	public ItemOfTheWeekTab(UserInterface parent) {
		super(parent);
		this.item = ctx.grandExchange.getItemOfTheWeek();
	}
	
	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		final Graphics2D g = (Graphics2D)g1;
		g.setRenderingHints(antialiasing);
		g.setColor(Color.BLACK);
		g.setFont(font2);
		g.drawString("Item of the week", 5, 25);
		if (item != null) {
			g.drawImage(ctx.imagePool.getImage(item.getLargeIconUrl()), 390, 40, null);
			g.setFont(font1);
			g.drawString("Name: " + item.getName(), 5, 60);
			g.drawString("Id: " + item.getId(), 5, 80);
			g.drawString("Price: " + item.getCurrentPrice(), 5, 100);
			g.drawString("Type: " + item.getType(), 5, 120);
			g.drawString("Description: " + item.getDescription(), 5, 140);
		}
	}

}

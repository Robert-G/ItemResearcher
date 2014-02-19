package itemresearcher.userinterface.tabs;

import itemresearcher.geitem.GEItem;
import itemresearcher.grandexchange.GrandExchange;
import itemresearcher.userinterface.UserInterface;
import itemresearcher.userinterface.autocomplete.AutoComplete;
import itemresearcher.userinterface.tabs.tab.Tab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class SearchTab extends Tab implements KeyListener {

	private static final long serialVersionUID = 2789552137153777618L;
	private final JTextField field;
	private final AutoComplete autoComplete;

	public SearchTab(UserInterface parent) {
		super(parent);
		this.field = new JTextField("enter item name here.", 20);
		this.field.setBounds(282, 10, 210, 15);
		this.field.selectAll();
		this.autoComplete = new AutoComplete(field);
		this.field.getDocument().addDocumentListener(autoComplete);
		this.field.setToolTipText("Enter item name then press enter.");
		this.field.addKeyListener(this);
		this.add(field);
	}
	
	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		final Graphics2D g = (Graphics2D)g1;
		g.setRenderingHints(antialiasing);
		g.setColor(Color.BLACK);
		g.setFont(font1);
		g.drawString("Search for an item to display its details", 5, 25);
		if (item != null) {
			g.drawImage(ctx.imagePool.getImage(item.getLargeIconUrl()), 390, 40, null);
			g.drawString("Name: " + item.getName(), 5, 60);
			g.drawString("Id: " + item.getId(), 5, 80);
			g.drawString("Price: " + item.getCurrentPrice(), 5, 100);
			g.drawString("Type: " + item.getType(), 5, 120);
			g.drawString("Description: " + item.getDescription(), 5, 140);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			final String text = field.getText();
			if (text == null || text.isEmpty()) {
				return;
			}
			final GEItem item = GrandExchange.lookUpItem(text);
			if (item != null) {
				super.item = item;
				repaint();
			} else {
				field.setText("Unable to find " + text + ".");
			}
			field.selectAll();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}

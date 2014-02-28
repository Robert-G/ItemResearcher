package itemresearcher.userinterface;

import itemresearcher.methodcontext.MethodContext;
import itemresearcher.userinterface.tabs.ItemOfTheWeekTab;
import itemresearcher.userinterface.tabs.SearchTab;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Robert G
 *
 */
public class UserInterface extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private final MethodContext ctx;
	private final JTabbedPane tabbedPane;
	private final ItemOfTheWeekTab iotwt;
	private final SearchTab searchTab;

	public UserInterface() {
		super(new GridLayout(1, 1));
		this.ctx = new MethodContext();
		this.tabbedPane = new JTabbedPane();
		this.iotwt = new ItemOfTheWeekTab(this);
		this.tabbedPane.addTab("Item of the week", this.iotwt);
		this.searchTab = new SearchTab(this);
		this.tabbedPane.addTab("Search", this.searchTab);
		this.add(tabbedPane);
	}

	public MethodContext getContext() {
		return ctx;
	}

	public void run() {
		final JFrame frame = new JFrame();
		frame.setTitle("ItemResearcher - A tool for researching items on the GrandExchange.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new UserInterface(), BorderLayout.CENTER);
		frame.setSize(520, 205);
		frame.setResizable(false);
		frame.setLocationRelativeTo(frame.getOwner());
		frame.setVisible(true);
	}

}


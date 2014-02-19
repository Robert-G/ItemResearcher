package itemresearcher;
import itemresearcher.userinterface.UserInterface;

import javax.swing.SwingUtilities;

public class ItemResearcher {

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new UserInterface());
	}

}

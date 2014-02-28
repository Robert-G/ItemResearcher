package itemresearcher;
import itemresearcher.userinterface.UserInterface;

import javax.swing.SwingUtilities;


/**
 * @author Robert G
 *
 */
public class ItemResearcher {

	/**
	 * A simple wrapper to launch the user interface.
	 * 
	 */
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new UserInterface());
	}

}

package itemresearcher.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Robert G
 *
 */
public class IO {

	private static final String user_agent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)";
	
	/**
	 * 
	 * @param webAddress
	 * @param regex
	 * @return
	 */
	public static String loadText(String webAddress, String regex) {
		final StringBuilder x = new StringBuilder();
		try {
			final URLConnection con = new URL(webAddress).openConnection();
			con.setRequestProperty("User-Agent", user_agent);
			final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (regex == null || inputLine.contains(regex)) {
					x.append(inputLine);
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x.length() > 0 ? x.toString() : null;
	}
	
	public static String loadText(String webAddress) {
		return loadText(webAddress, null);
	}

}

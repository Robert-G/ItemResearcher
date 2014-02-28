package itemresearcher.methods;

public class Strings {

	/**
	 * 
	 * @param startRegex
	 * @param endRegex
	 * @return
	 */
	public static String getString(String jsonArray, String startRegex, String endRegex) {
		final int start = jsonArray.indexOf(startRegex) + startRegex.length();
		final int end = jsonArray.indexOf(endRegex);
		return jsonArray.substring(start, end);
	}
	
}

package me.nrubin29.pastebinapi;

public class PastebinAPI {

	private String apikey;
	
	/**
	 * Constructor for PastebinAPI.
	 * @param apikey Your API key. Get one at http://pastebin.com/api/.
	 */
	public PastebinAPI(String apikey) {
		this.apikey = apikey;
	}
	
	/**
	 * Begin constructing a paste.
	 * @return A new instance of PastebinPasteContext.
	 */
	public PastebinPasteContext createPaste() {
		return new PastebinPasteContext(apikey);
	}
}
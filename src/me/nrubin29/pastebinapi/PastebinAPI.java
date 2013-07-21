package me.nrubin29.pastebinapi;

public class PastebinAPI {

	private String apikey;
	
	/**
	 * Constructor for PastebinAPI.
	 * @param apikey Your API key. Get yours from http://pastebin.com/api/
	 */
	public PastebinAPI(String apikey) {
		this.apikey = apikey;
	}
	
	/**
	 * Begin constructing a paste.
	 * @return A new instance of the Paste wrapper class.
	 */
	public Paste createPaste() {
		return new Paste(apikey);
	}
}
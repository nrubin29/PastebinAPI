package me.nrubin29.pastebinapi;

public class CreatePaste {
	
	private PastebinAPI api;
	
	protected CreatePaste(PastebinAPI api) {
		this.api = api;
	}

    private User user;
	private String text, name;
	private Format format;
	private PrivacyLevel privacylevel = PrivacyLevel.PUBLIC;
	private ExpireDate expiredate = ExpireDate.NEVER;
	
	/**
	 * Sets the text of the paste.
	 * @param text The text of the paste.
	 * @return The same instance of CreatePaste with the text set as given.
	 */
	public CreatePaste withText(String text) {
		this.text = text;
		return this;
	}

    protected CreatePaste withUser(User user) {
        this.user = user;
        return this;
    }

	/**
	 * Sets the name of the paste.
	 * @param pastename The name of the paste.
	 * @return The same instance of CreatePaste with the name set as given.
	 */
	public CreatePaste withName(String pastename) {
		this.name = pastename;
		return this;
	}
	
	/**
	 * Sets the paste format.
	 * @param pasteformat The paste format.
	 * @return The same instance of CreatePaste with the format set as given.
	 */
	public CreatePaste withFormat(Format pasteformat) {
		this.format = pasteformat;
		return this;
	}
	
	/**
	 * Sets the expiration date of the paste.
	 * @param pasteexpiredate The expiration date of the paste.
	 * @return The same instance of CreatePaste with the expiration date set as given.
	 */
	public CreatePaste withExpireDate(ExpireDate pasteexpiredate) {
		this.expiredate = pasteexpiredate;
		return this;
	}
	
	/**
	 * Sets the privacy level of the paste.
	 * @param privacylevel The privacy level of the paste.
	 * @return The same instance of CreatePaste with the privacy level set as given.
	 */
	public CreatePaste withPrivacyLevel(PrivacyLevel privacylevel) {
		this.privacylevel = privacylevel;
		return this;
	}
	
	/**
	 * Posts using the information given.
	 * @return A String representing either a URL or a String representing the error.
	 */
	public String post() throws PastebinException {
		return api.getUtils().post(argsToPOST())[0];
	}
	
	private String argsToPOST() {
		String args = "api_dev_key=" + api.getAPIKey() + "&api_option=paste&api_paste_code=" + text;
		if (name != null) args += "&api_paste_name=" + name;
		if (format != null) args += "&api_paste_format=" + format.getFormat();
		args += "&api_paste_expire_date=" + expiredate.getDate();
		args += "&api_paste_private=" + privacylevel.getLevel();
		if (user != null) args += "&api_user_key=" + user.getUserKey();
		return args;
	}
}
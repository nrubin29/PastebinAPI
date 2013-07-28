package me.nrubin29.pastebinapi;

import java.util.ArrayList;

public class PastebinAPI {

	private String apikey;
	private Utils utils;
	
	/**
	 * Constructor for PastebinAPI.
	 * @param apikey Your API key. Get yours from http://pastebin.com/api/
	 */
	public PastebinAPI(String apikey) {
		this.apikey = apikey;
		this.utils = new Utils();
	}
	
	/**
	 * Get the supplied API key.
	 * @return The supplied API key.
	 */
	public String getAPIKey() {
		return apikey;
	}
	
	/**
	 * Begin constructing a paste. To create a paste with a user, use the createPaste() method in the User class.
	 * @return A new instance of the CreatePaste class.
	 */
	public CreatePaste createPaste() {
		return new CreatePaste(this);
	}
	
	protected Utils getUtils() {
		return utils;
	}

    /**
     * Get a new User with a username and password.
     * @param username The username.
     * @param password The password.
     * @return A new instance of User with the username and password authenticated.
     */
    public User getUser(String username, String password) throws PastebinException {
        return new User(this, username, password);
    }

    public ArrayList<Paste> getTrendingPastes() throws PastebinException {
        return parse(utils.post("api_dev_key=" + apikey + "&api_option=trends"));
    }

    protected ArrayList<Paste> parse(String[] args) throws PastebinException {
        ArrayList<Paste> pastes = new ArrayList<Paste>();
        ArrayList<String> current = new ArrayList<String>();

        for (String str : args) {
            if (str != null && !str.equals("<paste>")) {
                if (str.equals("</paste>")) {
                    pastes.add(new Paste(this, current));
                    current = new ArrayList<String>();
                }
                else if (str.startsWith("<paste_")) current.add(str);
            }
        }
        return pastes;
    }
}
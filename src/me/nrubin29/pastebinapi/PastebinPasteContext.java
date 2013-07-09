package me.nrubin29.pastebinapi;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PastebinPasteContext {

	/**
	 * Represents a privacy level of public.
	 */
	public static final int PUBLIC = 0;
	
	/**
	 * Represents a privacy level of unlisted.
	 */
	public static final int UNLISTED = 1;
	
	/**
	 * Represents a privacy level of private.
	 */
	public static final int PRIVATE = 2;
	
	private String apikey = "";
	private URL baseURL;
	
	PastebinPasteContext(String apikey) {
		this.apikey = apikey;
		try { baseURL = new URL("http://pastebin.com/api/api_post.php"); }
		catch (Exception e) { }
	}
	
	private String text, username, password, pastename, pasteformat, pasteexpiredate;
	private int pasteprivate = -1;
	
	/**
	 * Sets the text of the paste.
	 * @param text The text of the paste.
	 * @return The same instance of PastebinPasteContext with the text set as given.
	 */
	public PastebinPasteContext withText(String text) {
		this.text = text;
		return this;
	}
	
	/**
	 * Sets the username of the paster.
	 * @param username The username of the paster.
	 * @return The same instance of PastebinPasteContext with the username set as given.
	 */
	public PastebinPasteContext withUsername(String username) {
		this.username = username;
		return this;
	}
	
	/**
	 * Sets the password of the paster.
	 * @param password The password of the paster.
	 * @return The same instance of PastebinPasteContext with the password set as given.
	 */
	public PastebinPasteContext withPassword(String password) {
		this.password = password;
		return this;
	}
	
	/**
	 * Sets the name of the paste.
	 * @param pastename The name of the paste.
	 * @return The same instance of PastebinPasteContext with the name set as given.
	 */
	public PastebinPasteContext withPasteName(String pastename) {
		this.pastename = pastename;
		return this;
	}
	
	/**
	 * Sets the format of the paste. See http://pastebin.com/api#5 for valid formats.
	 * @param pasteformat The format of the paste.
	 * @return The same instance of PastebinPasteContext with the format set as given.
	 */
	public PastebinPasteContext withPasteFormat(String pasteformat) {
		this.pasteformat = pasteformat;
		return this;
	}
	
	/**
	 * Sets the expiration date of the paste. See http://pastebin.com/api#6 for valid formats.
	 * @param pasteexpiredate The expiration date of the paste.
	 * @return The same instance of PastebinPasteContext with the expiration date set as given.
	 */
	public PastebinPasteContext withPasteExpireDate(String pasteexpiredate) {
		this.pasteexpiredate = pasteexpiredate;
		return this;
	}
	
	/**
	 * Sets the privacy level of the paste. Use the PUBLIC, UNLISTED, and PRIVATE static fields of this class.
	 * @param pasteprivate The privacy level of the paste.
	 * @return The same instance of PastebinPasteContext with the privacy level set as given.
	 */
	public PastebinPasteContext withPastePrivacySetting(int pasteprivate) {
		this.pasteprivate = pasteprivate;
		return this;
	}
	
	/**
	 * Posts using the information given.
	 * @return A String representing either a URL or a String representing the error.
	 */
	public String post() {
		try {
			HttpURLConnection connection = (HttpURLConnection) baseURL.openConnection();           
	    	connection.setDoOutput(true);
	    	connection.setDoInput(true);
	    	connection.setInstanceFollowRedirects(false); 
	    	connection.setRequestMethod("POST"); 
	    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	    	connection.setRequestProperty("charset", "utf-8");
	    	connection.setRequestProperty("Content-Length", "" + argsToPOST().getBytes().length);
	    	connection.setUseCaches(false);

	    	DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    	wr.writeBytes(argsToPOST());
	    	wr.flush();
	    	wr.close();
	    	connection.disconnect();
	    	
	    	Scanner s = new Scanner(connection.getInputStream());
	    	return s.nextLine();
		}
		catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}
	
	private String argsToPOST() {
		String args = "api_dev_key=" + apikey + "&api_option=paste&api_paste_code=" + text;
		if (pastename != null) args += "&api_paste_name=" + pastename;
		if (pasteformat != null) args += "&api_paste_format=" + pasteformat;
		if (pasteexpiredate != null) args += "&api_paste_expire_date=" + pasteexpiredate;
		if (pasteprivate != -1) args += "&api_paste_private=" + pasteprivate;
		if (username != null && password != null) {
			String loginargs = "api_dev_key=" + apikey + "&api_user_name=" + username + "&api_user_password=" + password; 
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL("http://pastebin.com/api/api_login.php").openConnection();           
		    	connection.setDoOutput(true);
		    	connection.setDoInput(true);
		    	connection.setInstanceFollowRedirects(false); 
		    	connection.setRequestMethod("POST"); 
		    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		    	connection.setRequestProperty("charset", "utf-8");
		    	connection.setRequestProperty("Content-Length", "" + loginargs.getBytes().length);
		    	connection.setUseCaches(false);

		    	DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		    	wr.writeBytes(loginargs);
		    	wr.flush();
		    	wr.close();
		    	connection.disconnect();
		    	
		    	Scanner s = new Scanner(connection.getInputStream());
		    	args += "&api_user_key=" + s.nextLine();
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		return args;
	}
}
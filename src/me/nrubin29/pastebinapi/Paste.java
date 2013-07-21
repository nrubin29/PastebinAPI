package me.nrubin29.pastebinapi;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Paste {
	
	private String apikey = "";
	private URL baseURL;
	
	protected Paste(String apikey) {
		this.apikey = apikey;
		try { baseURL = new URL("http://pastebin.com/api/api_post.php"); }
		catch (Exception e) { }
	}
	
	private String text, username, password, name;
	private Format format;
	private PrivacyLevel privacylevel = PrivacyLevel.PUBLIC;
	private ExpireDate expiredate = ExpireDate.NEVER;
	
	/**
	 * Sets the text of the paste.
	 * @param text The text of the paste.
	 * @return The same instance of Paste with the text set as given.
	 */
	public Paste withText(String text) {
		this.text = text;
		return this;
	}
	
	/**
	 * Sets the username of the paster.
	 * @param username The username of the paster.
	 * @return The same instance of Paste with the username set as given.
	 */
	public Paste withUsername(String username) {
		this.username = username;
		return this;
	}
	
	/**
	 * Sets the password of the paster.
	 * @param password The password of the paster.
	 * @return The same instance of Paste with the password set as given.
	 */
	public Paste withPassword(String password) {
		this.password = password;
		return this;
	}
	
	/**
	 * Sets the name of the paste.
	 * @param pastename The name of the paste.
	 * @return The same instance of Paste with the name set as given.
	 */
	public Paste withName(String pastename) {
		this.name = pastename;
		return this;
	}
	
	/**
	 * Sets the paste format.
	 * @param pasteformat The paste format.
	 * @return The same instance of Paste with the format set as given.
	 */
	public Paste withFormat(Format pasteformat) {
		this.format = pasteformat;
		return this;
	}
	
	/**
	 * Sets the expiration date of the paste.
	 * @param pasteexpiredate The expiration date of the paste.
	 * @return The same instance of Paste with the expiration date set as given.
	 */
	public Paste withExpireDate(ExpireDate pasteexpiredate) {
		this.expiredate = pasteexpiredate;
		return this;
	}
	
	/**
	 * Sets the privacy level of the paste.
	 * @param pasteprivate The privacy level of the paste.
	 * @return The same instance of Paste with the privacy level set as given.
	 */
	public Paste withPrivacyLevel(PrivacyLevel privacylevel) {
		this.privacylevel = privacylevel;
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
		if (name != null) args += "&api_paste_name=" + name;
		if (format != null) args += "&api_paste_format=" + format.getFormat();
		if (expiredate != null) args += "&api_paste_expire_date=" + expiredate.getDate();
		args += "&api_paste_private=" + privacylevel.getLevel();
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
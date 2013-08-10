package me.nrubin29.pastebinapi;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    private PastebinAPI api;

    protected Utils(PastebinAPI api) {
        this.api = api;
    }
	
	protected String[] sendToURL(URL url, String text) throws PastebinException {
		try {
            boolean arg = (text != null);
            if (arg) text = "api_dev_key=" + api.getAPIKey() + text;

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    	if (arg) connection.setDoOutput(true);
	    	connection.setDoInput(true);
	    	connection.setInstanceFollowRedirects(false); 
	    	connection.setRequestMethod("POST"); 
	    	if (arg) connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    	connection.setRequestProperty("charset", "utf-8");
	    	if (arg) connection.setRequestProperty("Content-Length", "" + text.getBytes().length);
	    	connection.setUseCaches(false);

	    	if (arg) {
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(text);
                wr.flush();
                wr.close();
            }

            connection.disconnect();
	    	
	    	Scanner s = new Scanner(connection.getInputStream());
	    	
	    	ArrayList<String> output = new ArrayList<String>();
	    	
	    	while (s.hasNext()) {
                String next = s.nextLine();
	    		output.add(next);
	    	}

	    	if (output.get(0).startsWith("Bad API request")) {
                throw new PastebinException(output.get(0));
            }
	    	
	    	return output.toArray(new String[output.size()]);
		}
		catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}

    public String[] post(String url, String text) throws PastebinException {
        try {
            return sendToURL(new URL(url), text);
        } catch (MalformedURLException e) { return null; }
    }

	public String[] post(String text) throws PastebinException {
		return post("http://pastebin.com/api/api_post.php", text);
    }
}
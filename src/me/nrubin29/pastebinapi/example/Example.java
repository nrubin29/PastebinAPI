package me.nrubin29.pastebinapi.example;

import me.nrubin29.pastebinapi.ExpireDate;
import me.nrubin29.pastebinapi.Format;
import me.nrubin29.pastebinapi.PastebinAPI;
import me.nrubin29.pastebinapi.Paste;
import me.nrubin29.pastebinapi.PrivacyLevel;

public class Example {

	public static void main(String[] args) {
		PastebinAPI api = new PastebinAPI("Put your developer key here");
		
		Paste paste = api.createPaste()
				.withUsername("Account username") // if you don't specify a username and password,
				.withPassword("Account password") // the paste will be pasted as Guest.
				.withName("Paste name")
				.withFormat(Format.None)
				.withPrivacyLevel(PrivacyLevel.PUBLIC)
				.withExpireDate(ExpireDate.NEVER)
				.withText("Text to paste");
		
		String url = paste.post(); // The post method posts the paste and returns the URL.
								   // You may also get an error message.
		
		System.out.println(url);
	}
}
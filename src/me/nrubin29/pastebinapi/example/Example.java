package me.nrubin29.pastebinapi.example;

import me.nrubin29.pastebinapi.*;

import java.util.ArrayList;

public class Example {

	public static void main(String[] args) throws PastebinException {

        /* PastebinAPI initialization */

		PastebinAPI api = new PastebinAPI("Put your developer key here");

        /* Getting a user */

        User user = api.getUser("username", "password"); // Without get an instance of User,
                                                         // you can only get trending pastes and paste as a guest.

        /* Getting user information */

        String userEmail = user.getEmail(); // There are plenty of other get methods in the User class.

        /* Creating a paste */
		
		CreatePaste paste = user.createPaste() // You can call api.createPaste() to create a paste as a guest.
				.withName("Paste name")
				.withFormat(Format.None)
				.withPrivacyLevel(PrivacyLevel.PUBLIC)
				.withExpireDate(ExpireDate.NEVER)
				.withText("Text to paste");

		String url = paste.post(); // The post method posts the paste and returns the URL.

        /* Getting pastes from a user */

        Paste[] pastes = user.getPastes(); // You can also specify a limit on the number of results returned.

        /* Getting raw text from a paste */

        Paste firstPaste = pastes[0];

        String[] rawText = firstPaste.getText(); // You can also get a String containing the raw text by adding a String to the getText() method.
                                                 // There are plenty of other get methods in the Paste class.

        /* Removing a user's paste */

        user.removePaste("pastekey"); // You can also give it a Paste.

        /* Get trending pastes */

        Paste[] trending = api.getTrendingPastes();

        /* NOTE: Various methods may throw a PastebinException, which will contain the error message Pastebin returns. */
	}
}


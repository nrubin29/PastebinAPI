package me.nrubin29.pastebinapi.example;

import me.nrubin29.pastebinapi.*;

import java.util.ArrayList;

public class Example {

	public static void main(String[] args) throws PastebinException {

        /* PastebinAPI Initialization */

		PastebinAPI api = new PastebinAPI("Put your developer key here");

        /* Getting a User */

        User user = api.getUser("username", "password"); // Without get an instance of User,
                                                         // you can only get trending pastes and paste as a guest.

        /* Creating a paste */
		
		CreatePaste paste = user.createPaste() // You can call api.createPaste() to create a paste as a guest.
				.withName("Paste name")
				.withFormat(Format.None)
				.withPrivacyLevel(PrivacyLevel.PUBLIC)
				.withExpireDate(ExpireDate.NEVER)
				.withText("Text to paste");
		
		String url = paste.post(); // The post method posts the paste and returns the URL.

        /* Getting pastes from a user */

        ArrayList<Paste> pastes = user.getPastes(); // You can also specify a limit on the number of results returned.

        /* Removing a user's paste */

        user.removePaste(pastes.get(0));

        /* Get trending pastes */

        ArrayList<Paste> trending = api.getTrendingPastes();

        /* NOTE: You can get more information by calling get methods in the Paste and User classes. */
        /* NOTE: Various methods may throw a PastebinException, which will contain the message Pastebin returns. */
	}
}


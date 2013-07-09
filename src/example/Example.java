package example;

import me.nrubin29.pastebinapi.PastebinAPI;
import me.nrubin29.pastebinapi.PastebinPasteContext;

public class Example {

	public static void main(String[] args) {
		PastebinAPI api = new PastebinAPI("Put your developer key here!");
		
		PastebinPasteContext context = api.createPaste()
				.withUsername("Account username")
				.withPassword("Account password")
				.withPastePrivacySetting(PastebinPasteContext.PUBLIC)
				.withPasteExpireDate("Valid expiration date")
				.withPasteFormat("Valid paste format")
				.withPasteName("Paste name")
				.withPasteText("Here is the text that will be posted!");
		
		String url = context.post(); // This is the URL I get when I post the information I've given.
	}
}
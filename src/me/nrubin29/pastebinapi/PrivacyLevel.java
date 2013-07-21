package me.nrubin29.pastebinapi;

public enum PrivacyLevel {

	PUBLIC(0),
	UNLISTED(1),
	PRIVATE(2);
	
	private int level;
	
	private PrivacyLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
}
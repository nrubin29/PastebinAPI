package me.nrubin29.pastebinapi;

public enum ExpireDate {

	NEVER("N"),
	TEN_MINUTES("10M"),
	ONE_HOUR("1H"),
	ONE_DAY("1D"),
	ONE_WEEK("1W"),
	TWO_WEEKS("2W"),
	ONE_MONTH("1M");
	
	private String date;
	
	private ExpireDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
}
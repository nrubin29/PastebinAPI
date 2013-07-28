package me.nrubin29.pastebinapi;

public enum AccountType {
    NORMAL, PRO;

    public static AccountType valueOf(int i) {
        switch(i) {
            case 0: return NORMAL;
            case 1: return PRO;
            default: return null;
        }
    }
}
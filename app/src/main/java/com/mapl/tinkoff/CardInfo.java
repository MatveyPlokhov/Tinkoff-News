package com.mapl.tinkoff;

public class CardInfo {
    Integer id;
    long milliseconds;
    String text;

    public CardInfo(Integer id, long milliseconds, String text) {
        this.id = id;
        this.milliseconds = milliseconds;
        this.text = text;
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}

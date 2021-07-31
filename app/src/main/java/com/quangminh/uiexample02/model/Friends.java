package com.quangminh.uiexample02.model;

public class Friends {
    String name;
    int avt;
    String inbox;
    String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public String getInbox() {
        return inbox;
    }

    public void setInbox(String inbox) {
        this.inbox = inbox;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Friends() {
    }

    public Friends(String name, int avt, String inbox, String date) {
        this.name = name;
        this.avt = avt;
        this.inbox = inbox;
        this.date = date;
    }
}

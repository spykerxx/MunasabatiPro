package com.example.Munasabati;

public class EventNotification {
    private String clientName;
    private String title;
    private String comment;
    private String time;

    public EventNotification(String clientName, String title, String comment, String time) {
        this.clientName = clientName;
        this.title = title;
        this.comment = comment;
        this.time=time;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package com.example.Munasabati;

public class Invitation {
    private String clientName;
    private String eventName;
    private String time;
    private String qr;

    public Invitation(String clientName, String eventName, String time) {
        this.clientName = clientName;
        this.eventName = eventName;
        this.time=time;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}

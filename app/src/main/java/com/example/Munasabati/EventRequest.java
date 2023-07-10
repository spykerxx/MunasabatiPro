package com.example.Munasabati;

public class EventRequest {
    private String clientName;
    private String eventName;

    public EventRequest(String clientName, String eventName) {
        this.clientName = clientName;
        this.eventName = eventName;
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
}

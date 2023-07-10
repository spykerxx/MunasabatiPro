package com.example.Munasabati;

public class ServiceNotification {
    private String eventName;
    private String serviceName;
    private String serviceProviderName;

    public ServiceNotification(String eventName, String serviceName, String serviceProviderName) {
        this.eventName = eventName;
        this.serviceName = serviceName;
        this.serviceProviderName = serviceProviderName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }
}

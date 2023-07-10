package com.example.Munasabati;

public class Service {
    private String name;
    private String price;
    private String minDuration;
    private String photo;
    private String detail;
    private String providerName;

    public Service(String name, String price, String minDuration, String photo, String detail, String providerName) {
        this.name = name;
        this.price = price;
        this.minDuration = minDuration;
        this.photo = photo;
        this.detail = detail;
        this.providerName = providerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(String minDuration) {
        this.minDuration = minDuration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
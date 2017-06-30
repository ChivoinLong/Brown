package com.thesis.brown.brown.store_list;

import java.io.Serializable;

public class ListStoreListModel implements Serializable{

    private String title, road, phone, link, openingTime;
    private int imageResID;
    private double longitude , latitude;

    public ListStoreListModel(String title, String road, String phone, String link, String openingTime, int imageResID, double latitude, double longitude) {
        this.title = title;
        this.road = road;
        this.phone = phone;
        this.link = link;
        this.openingTime = openingTime;
        this.imageResID = imageResID;
        this.latitude = latitude;
        this.longitude = longitude;
    }




    public String getTitle() {
        return title;
    }

    public String getRoad() {
        return road;
    }

    public String getPhone() {
        return phone;
    }

    public String getLink() {
        return link;
    }

    public int getImage() {
        return imageResID;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}

package com.thesis.brown.brown.store_list;

import java.io.Serializable;

public class ListStoreListModel implements Serializable{

    private String title, road, phone, link, imgURL, openingTime;
    private double longitude , latitude;



    public ListStoreListModel(String title, String road, String phone, String link, String imgURL, String openingTime, double longitude, double latitude) {
        this.title = title;
        this.road = road;
        this.phone = phone;
        this.link = link;
        this.imgURL = imgURL;
        this.openingTime = openingTime;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getImgURL() {
        return imgURL;
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

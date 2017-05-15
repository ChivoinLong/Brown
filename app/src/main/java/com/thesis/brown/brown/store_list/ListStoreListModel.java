package com.thesis.brown.brown.store_list;

import java.io.Serializable;

public class ListStoreListModel implements Serializable{

    private String title, road, phone, link, imgURL, openingTime;

    public ListStoreListModel(String title, String road, String phone, String link, String imgURL, String openingTime) {
        this.title = title;
        this.road = road;
        this.phone = phone;
        this.link = link;
        this.imgURL = imgURL;
        this.openingTime = openingTime;
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
}

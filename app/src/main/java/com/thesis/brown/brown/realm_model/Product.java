package com.thesis.brown.brown.realm_model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Obi-Voin Kenobi on 11-Jul-17.
 */

public class Product extends RealmObject {

    @PrimaryKey
    private String _id;
    private String name, short_description, description, img_url;
    private RealmList<Price> price;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public RealmList<Price> getPrice() {
        return price;
    }

    public void setPrice(RealmList<Price> price) {
        this.price = price;
    }
}

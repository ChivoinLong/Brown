package com.thesis.brown.brown.realm_model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Obi-Voin Kenobi on 11-Jul-17.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private String _id;
    private String name, description, img_url;
    private Date updatedAt, createdAt;
    private RealmList<Subcategory> subcategory;
    private RealmList<Product> products;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public RealmList<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(RealmList<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }

    public RealmList<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }
}

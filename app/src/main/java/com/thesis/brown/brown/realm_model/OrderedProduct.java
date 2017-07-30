package com.thesis.brown.brown.realm_model;

import io.realm.RealmObject;

/**
 * Created by Obi-Voin Kenobi on 16-Jul-17.
 */

public class OrderedProduct extends RealmObject {
    String id, size;
    Integer quantity;

    public OrderedProduct() {
    }

    public OrderedProduct(String id, String size, Integer quantity) {
        this.id = id;
        this.size = size;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package com.thesis.brown.brown.realm_model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Obi-Voin Kenobi on 11-Jul-17.
 */

public class Price extends RealmObject {

    @PrimaryKey
    private String _id;
    private String name;
    private Double amount;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

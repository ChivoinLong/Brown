package com.thesis.brown.brown.model;

import java.util.HashMap;

public class Product {

    private String _id, _name, _desc, _imgUrl;
    private HashMap<String, Double> _price;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public String get_imgUrl() {
        return _imgUrl;
    }

    public void set_imgUrl(String _imgUrl) {
        this._imgUrl = _imgUrl;
    }

    public HashMap<String, Double> get_price() {
        return _price;
    }

    public void set_price(HashMap<String, Double> _price) {
        this._price = _price;
    }
}

package com.thesis.brown.brown;

/**
 * Created by lolzzlolzz on 5/21/17.
 */

public class CategoryListModel {
    private String type, name, id;

    public CategoryListModel(String type, String name, String id) {
        this.type = type;
        this.name = name;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

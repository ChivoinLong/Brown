package com.thesis.brown.brown.model;

/**
 * Created by lolzzlolzz on 5/21/17.
 */

public class Category {
    private String id, name, type, parentId;

    public Category(String id, String name, String type, String parent) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getParent() {
        return parentId;
    }
}

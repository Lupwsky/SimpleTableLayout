package com.lupw.simpletablelayout;

/**
 * Created by lupengwei on 2017/11/15.
 * Admin Lupw
 */

public class LeftBean {
    private String id;
    private String name;
    private boolean isSelected;

    LeftBean() {
    }

    public LeftBean(String id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

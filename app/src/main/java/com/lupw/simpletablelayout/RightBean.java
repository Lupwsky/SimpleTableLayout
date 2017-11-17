package com.lupw.simpletablelayout;

/**
 * Created by lupengwei on 2017/11/15.
 * Admin Lupw
 */

public class RightBean {
    private String id;
    private String name;
    private boolean isTitle;
    private boolean isSelected;

    RightBean() {
    }


    public RightBean(String id, String name, boolean isTitle) {
        this.id = id;
        this.name = name;
        this.isTitle = isTitle;
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


    public boolean isTitle() {
        return isTitle;
    }


    public void setTitle(boolean title) {
        isTitle = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

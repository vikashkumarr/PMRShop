package com.mahindra.pmrshop.models;

public class GrinderModel {

    private String grinderName;
    private int grinderImage;

    public GrinderModel(String grinderName, int grinderImage) {
        this.grinderName = grinderName;
        this.grinderImage = grinderImage;
    }

    public String getGrinderName() {
        return grinderName;
    }

    public void setGrinderName(String grinderName) {
        this.grinderName = grinderName;
    }

    public int getGrinderImage() {
        return grinderImage;
    }

    public void setGrinderImage(int grinderImage) {
        this.grinderImage = grinderImage;
    }
}

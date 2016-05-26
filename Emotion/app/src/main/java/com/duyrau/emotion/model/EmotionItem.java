package com.duyrau.emotion.model;

/**
 * Created by dj81hc on 5/26/2016.
 */
public class EmotionItem {

    private String name;
    private Integer imageId;

    public EmotionItem(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}

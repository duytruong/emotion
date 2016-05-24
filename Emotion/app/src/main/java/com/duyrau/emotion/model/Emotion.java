package com.duyrau.emotion.model;

/**
 * Created by duyrau on 22/5/2016.
 */
public class Emotion {
    private String name;
    private Integer imageId;

    public Emotion(Integer imageId) {
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

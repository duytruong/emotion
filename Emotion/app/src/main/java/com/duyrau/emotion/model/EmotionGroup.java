package com.duyrau.emotion.model;

import java.util.List;

/**
 * Created by duyrau on 22/5/2016.
 */
public class EmotionGroup {

    private String name;
    private Integer imageId;
    private List<EmotionItem> items;

    public EmotionGroup(Integer imageId) {
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

    public List<EmotionItem> getItems() {
        return items;
    }

    public void setItems(List<EmotionItem> items) {
        this.items = items;
    }
}

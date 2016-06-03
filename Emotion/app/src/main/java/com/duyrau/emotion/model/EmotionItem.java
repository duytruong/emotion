package com.duyrau.emotion.model;

/**
 * Created by dj81hc on 5/26/2016.
 */
public class EmotionItem {

    private String name;
    private Integer imageId;
    private int audioId;

    public EmotionItem(String name, Integer imageId, int audioId) {
        this.name = name;
        this.imageId = imageId;
        this.audioId = audioId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
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

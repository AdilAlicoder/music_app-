package com.india.audio_music;

public class fav_model {
    private String title;
    private String key;

    public fav_model(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

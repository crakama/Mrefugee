package com.crakama.refugee.database;

/**
 * Created by crakama on 11/14/2016.
 */

public class RepartChildModel {
    public RepartChildModel(String title, String body, int repimage) {
        this.title = title;
        this.body = body;
        this.repimage = repimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String title, body;

    public int getRepimage() {
        return repimage;
    }

    public void setRepimage(int repimage) {
        this.repimage = repimage;
    }

    private int repimage;

}

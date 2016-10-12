package com.crakama.refugee.database;

/**
 * Created by cate.rakama@gmail.com on 9/15/2016.
 */
public class NoticeBoardModel {
    private String newsHead;
    private String newsBody;
    private String newsorganization;

    public void setOrganization(String newsorganization) {
        this.newsorganization = newsorganization;
    }



    public String getOrganization() {
        return newsorganization;
    }


    public NoticeBoardModel() {
    }

    public NoticeBoardModel(String newsHead, String newsBody, String newsorganization) {
        this.newsHead = newsHead;
        this.newsBody = newsBody;
        this.newsorganization = newsorganization;

    }

    public String getNewsHead() {
        return newsHead;
    }

    public void setNewsHead(String nws_name){
        this.newsHead = nws_name;

    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }


}

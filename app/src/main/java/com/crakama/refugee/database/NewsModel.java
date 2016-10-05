package com.crakama.refugee.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crakama.refugee.Message;

/**
 * Created by cate.rakama@gmail.com on 9/15/2016.
 */
public class NewsModel {
    String newsHead;
    String newsBody;
    String newsorganization;

    public void setOrganization(String newsorganization) {
        this.newsorganization = newsorganization;
    }



    public String getOrganization() {
        return newsorganization;
    }


    public NewsModel() {
    }

    public NewsModel(String newsHead, String newsBody, String newsorganization) {
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

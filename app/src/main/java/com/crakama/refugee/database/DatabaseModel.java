package com.crakama.refugee.database;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.crakama.refugee.R;

/**
 * Created by User on 10/3/2016.
 */

public class DatabaseModel extends RecyclerView.ViewHolder{

    String title;
    String description;
    String Image;
    public TextView newsHead, newsBody;

    View mView;
    public DatabaseModel(View itemView) {
        super(itemView);
        this.newsHead = (TextView) itemView.findViewById(R.id.listview_item_title);
        this.newsBody = (TextView) itemView.findViewById(R.id.listview_item_short_description);
        this.description = description;
        this.Image = Image;
        mView = itemView;
    }

//    public void setNewsHead(String newsHead){
//        //this.newsHead = newsHead;
//        TextView field = (TextView) mView.findViewById(R.id.listview_item_title);
//        field.setText(newsHead);
//    }
//
//
//    public void setNewsBody(String newsBody) {
//        this.newsBody = newsBody;
//        TextView field = (TextView) mView.findViewById(R.id.listview_item_short_description);
//        field.setText(newsBody);
//    }




}

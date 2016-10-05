package com.crakama.refugee.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.crakama.refugee.R;
import com.crakama.refugee.database.NewsModel;

import java.util.ArrayList;

/**
 * Created by cate.rakama@gmail.com on 9/9/2016.
 */
public class DBAdapter extends BaseAdapter{

   Context cxt;
    ArrayList<NewsModel> newsArraylist;

    public DBAdapter(Context c, ArrayList<NewsModel> newsArraylist) {
        this.cxt =c;
        this.newsArraylist = newsArraylist;
    }

    @Override
    public int getCount() {
        return newsArraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return newsArraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(cxt).inflate(R.layout.fragment_dashboard_imagetext,parent,false);
        }

        TextView txtNewsHead = (TextView) convertView.findViewById(R.id.listview_item_title);
        TextView txtNewsBody = (TextView) convertView.findViewById(R.id.listview_item_short_description);
        TextView txtNewsOrg= (TextView) convertView.findViewById(R.id.listview_item_organization);

        final NewsModel dbModel = (NewsModel) this.getItem(position);

        txtNewsHead.setText(dbModel.getNewsHead());
        txtNewsBody.setText(dbModel.getNewsBody());
        txtNewsOrg.setText(dbModel.getOrganization());

        /**
         * ON ITEM CLICK
         */
     convertView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Toast.makeText(cxt, dbModel.getNewsHead(), Toast.LENGTH_SHORT).show();
         }
     });



        return convertView;
    }





}//Ends class DBAdapter

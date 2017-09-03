package com.dadaabcamps.mrefugee;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by dadaabcamps on 11/16/2016.
 */

public class PicassoClient {

    Context ctx;

    public PicassoClient(Context ctx) {
        this.ctx = ctx;
    }


    public static  void downloadProductImage(Context context, String imageurl, ImageView imageView){
        if(imageurl != null && imageurl.length() > 0){
            Picasso.with(context).load(imageurl).placeholder(R.mipmap.defaultplaceholder).into(imageView);
        }else{
            Picasso.with(context).load(R.mipmap.defaultplaceholder).into(imageView);
        }
    }
}

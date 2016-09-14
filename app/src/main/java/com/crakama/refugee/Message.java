package com.crakama.refugee;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cate.rakama@gmail.com on 9/14/2016.
 */
public class Message {

    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}


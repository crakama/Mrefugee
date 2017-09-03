package com.dadaabcamps.mrefugee;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by cate.rakama@gmail.com on 9/21/2016.
 */
public class Mrefugee extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

}

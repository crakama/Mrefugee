package com.crakama.refugee.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.crakama.refugee.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DBHelperAdapter {
  DatabaseReference db;
    Boolean saved;
    ArrayList<DB> newslist = new ArrayList<>();

/**
 * DB REFERENCE
 */

    public DBHelperAdapter(DatabaseReference db){
        this.db = db;
    }

    public Boolean save(DB news){
        if(news == null){
            saved = false;
        }else{
            try {
                db.child("DB").push().setValue(news);
                saved = true;
            } catch (DatabaseException e) {

                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
   /**IMPLEMENT FETCH FUNCTION AND FILL THE ARRAYLIST  */
    private void fetchData(DataSnapshot dataSnapshot){
        newslist.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            DB news = ds.getValue(DB.class);
            newslist.add(news);
        }
    }

    /**
     * RETRIEVE NEWS
     */
    public ArrayList<DB> retrieveNews(){
       db.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
             fetchData(dataSnapshot);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               fetchData(dataSnapshot);
           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        return newslist;
    }
}








package com.crakama.refugee.database;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DBOperationsHelper {
  DatabaseReference dbref;
    Boolean saved;
    ArrayList<DBModel> newsArraylist = new ArrayList<>();

/**
 * DBModel REFERENCE
 */

    public DBOperationsHelper(DatabaseReference db){
        this.dbref = db;
    }

    public Boolean save(DBModel news){
        if(news == null){
            saved = false;
        }else{
            try {
                dbref.child("DBModel").push().setValue(news);
                saved = true;
            } catch (DatabaseException e) {

                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
   /**IMPLEMENT FETCH FUNCTION THAT FILLS THE ARRAYLIST  */
    private void fetchData(DataSnapshot dataSnapshot){
        newsArraylist.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            DBModel news = ds.getValue(DBModel.class);
            newsArraylist.add(news);
            Log.v("FIREBASE RETRIEVE", "index=" + ds.getValue(DBModel.class));
        }


    }

    /**
     * RETRIEVE NEWS
     */
    public ArrayList<DBModel> retrieveNews(){
       dbref.addChildEventListener(new ChildEventListener() {
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
        return newsArraylist;
    }
}








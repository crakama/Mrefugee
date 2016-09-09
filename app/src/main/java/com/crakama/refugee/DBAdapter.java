package com.crakama.refugee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

/**
 * Created by cate.rakama@gmail.com on 9/9/2016.
 */
public class DBAdapter {

    //COLUMMS
    static final String ROWID = "id";
    static final String CAMPNAME = "campname";
    static final String LOCATION = "location";
    static final String TAG = "DBAdapter";

    //
   static  final String DBNAME = "refugedb";
      static final String TBNAME = "campstable";
      static final int DBVERSION = '1';


   static final String CREATE_TB = "CREATE TABLE campstable(id INTEGER PRIMARY KEY AUTOINCREMENT, + " +
           "campname TEXT NOT NULL, location TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase sqlitedatabase;
    DBHelper helper;

    public DBAdapter (Context ctx){

        this.c=ctx;
    }

    //Inner helper DB class
    private static class DBHelper  extends SQLiteOpenHelper {
        public DBHelper(Context context, String DBname, SQLiteDatabase.CursorFactory factory, int DBversion) {
            super(context, DBname, null, DBversion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqlitedatabase) {
            try
            {
                sqlitedatabase.execSQL(CREATE_TB);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int i1) {
            Log.w(TAG, "Upgrading your sql DB");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS");
            onCreate(sqlitedatabase);
        }


    }// Ends class DBHelper

        //Open the database
        public DBAdapter openDB(){
            try{
                sqlitedatabase = helper.getWritableDatabase();

            }catch (SQLException e){
                e.printStackTrace();
            }
           return this;
        }

        //Close the database
        public void close(){
         helper.close();
        }

        //Insert Into Table
        public long add(String campname, String loc) {
            try {
                ContentValues cv = new ContentValues();
                cv.put(CAMPNAME, campname);
                cv.put(LOCATION, loc);
                return sqlitedatabase.insert(CAMPNAME, ROWID, cv);
            } catch (SQLException e) {

            }
            return 0;
        }

            //GET ALL VALUES
            public Cursor getAllCampDetails() {
            String[] columns = {ROWID, CAMPNAME,LOCATION};
               return sqlitedatabase.query(TBNAME, columns,null,null,null, null, null);

        }







}//Ends class DBAdapter

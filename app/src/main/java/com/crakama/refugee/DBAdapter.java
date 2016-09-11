package com.crakama.refugee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by cate.rakama@gmail.com on 9/9/2016.
 */
public class DBAdapter {

//    SQLiteDatabase sqLiteDatabase;
//    //COLUMMS
//    static final String ROWID = "_id";
//    static final String CAMPNAME = "campname";
//    static final String LOCATION = "location";
//    static final String TAG = "DBAdapter";
//
//    //
//   static  final String DBNAME = "refugedb.sqlite";
//      static final String TBNAME = "campstable";
//      static final int DBVERSION = '1';
//
//
//
//
//    public DBAdapter(Context context) {
//        super(context, DBNAME, null, DBVERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        updateMyDatabase(sqLiteDatabase, 0, DBVERSION);
//
//
//       }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//
//        updateMyDatabase(sqLiteDatabase, oldVersion, newVersion);
//
//
//    }
//
//    private void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        if (oldVersion < 1) {
//            sqLiteDatabase.execSQL("CREATE TABLE CAMPSTABLE ("
//                    +  " _id INTEGER PRIMARY KEY AUTOINCREMENT , "
//                    +  "CAMPNAME TEXT, "
//                    +  "LOCATION TEXT;");
//        }
//        if (oldVersion > 1) {
//            // Drop older books table if existed
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS campstable");
//            // create fresh camps table
//            sqLiteDatabase.execSQL("CREATE TABLE CAMPSTABLE ("
//                    +  " _id INTEGER PRIMARY KEY AUTOINCREMENT , "
//                    +  "CAMPNAME TEXT, "
//                    +  "LOCATION TEXT;");
//
//
//        }
//    }
//
//    public long insertCampData(SQLiteDatabase sqLiteDatabase, String campname, String locaton) {
//        try{
//            // 1. get reference to writable DB
//
//            sqLiteDatabase = this.getWritableDatabase();
//            ContentValues campValues = new ContentValues();
//            campValues.put("CAMPNAME", campname);
//            campValues.put("LOCATION", locaton);
//            sqLiteDatabase.insert("CAMPSTABLE", null, campValues);
//        }catch(SQLiteException e) {
//
//            e.printStackTrace();
//            Log.d("DATABASE UNAVAILABLE", e.toString());
//        }
//        sqLiteDatabase.close();
//            return 0;
//    }
//
//
//    public Cursor getAllCampDetails() {
//             sqLiteDatabase = this.getReadableDatabase();
//
//             String[] columns = {ROWID, CAMPNAME,LOCATION};
//              return sqLiteDatabase.query(TBNAME, columns,null,null,null, null, null);
//
//        }



//    public void addCamp(String campname, String loc){
//        //for logging
//        Log.d("ADD CAMP", campname.toString());
//        Log.d("ADD LOCATION", loc.toString());
//
//        // 1. get reference to writable DB
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        // 2. create ContentValues to add key "column"/value
//        ContentValues cv = new ContentValues();
//                cv.put(CAMPNAME, campname);
//                cv.put(LOCATION, loc);
//
//        // 3. insert
//                sqLiteDatabase.insert(TBNAME,
//                                       ROWID,
//                                       cv);
//
//        // 4. close
//        sqLiteDatabase.close();
//    }



    //COLUMMS
    static final String ROWID = "id";
    static final String CAMPNAME = "campname";
    static final String LOCATION = "location";
    static final String TAG = "DBAdapter";

    //
   static  final String DBNAME = "refugedb";
      static final String TBNAME = "campstable";
      static final int DBVERSION = '1';


   static String CREATE_TB =
           "CREATE TABLE " +
           TBNAME + " (" +
           ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
           CAMPNAME + "  TEXT NOT NULL," +
           LOCATION + " TEXT NOT NULL" + ")";

    Context c;
    SQLiteDatabase sqlitedatabase;
    DBHelper dbHelper;

    public DBAdapter (Context ctx){

        this.c=ctx;
        dbHelper = new DBHelper(c);
    }

    //Inner helper DB class
    private static class DBHelper  extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqlitedatabase) {
            try
            {
                //sqlitedatabase.execSQL(CREATE_TB);
                sqlitedatabase.execSQL("CREATE TABLE CAMPSTABLE ("
                                       +  " _id INTEGER PRIMARY KEY AUTOINCREMENT , "
                                       +  "CAMPNAME TEXT, "
                                       +  "LOCATION TEXT;");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlitedatabase, int oldversion, int newversion) {
            Log.w(TAG, "Upgrading your sql DB");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS campstable");
            onCreate(sqlitedatabase);
        }


    }// Ends class DBHelper

        //Open the database
        public DBAdapter openDB(){
            try{
                //sqlitedatabase = helper.getWritableDatabase();
                dbHelper = new DBHelper(c);
                sqlitedatabase = dbHelper.getWritableDatabase();

            }catch (SQLException e){
                e.printStackTrace();
            }
           return this;
        }

        //Close the database
        public void close(){
            dbHelper = new DBHelper(c);
            dbHelper.close();
        }

        //Insert Into Table
        public long add(String campname, String loc) {
            try {
                ContentValues cv = new ContentValues();
                cv.put(CAMPNAME, campname);
                cv.put(LOCATION, loc);
                sqlitedatabase.insert(TBNAME, ROWID, cv);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            return 0;
        }

            //GET ALL VALUES
            public Cursor getAllCampDetails() {

            String[] columns = {ROWID, CAMPNAME,LOCATION};
               return sqlitedatabase.query(TBNAME, columns,null,null,null, null, null);

        }







}//Ends class DBAdapter

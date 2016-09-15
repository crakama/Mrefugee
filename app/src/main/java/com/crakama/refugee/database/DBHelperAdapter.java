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

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DBHelperAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dadaab.db";
    private static final String TABLE_NAME = "login";
    private static final String COL_ID = "login";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASS = "pass";

    private static final String CREATE_TABLE = "create table contacts (id integer primary key not null auto_increment , " +
                                        "name text not null , email text not null , username text not null , pass text not null);";

    SQLiteDatabase db;

    public DBHelperAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public void insertUser(DB b){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, b.getUsername());
        values.put(COL_EMAIL, b.getEmail());
        values.put(COL_PASS, b.getPass());

        db.insert(TABLE_NAME, null , values);
    }


//    Context ctx;
//    DBHelper helper;
//
//    public DBHelperAdapter(Context context){
//        this.ctx = context;
//        helper = new DBHelper(context);
//    }
//
//    public long insertData(String name, String location){
//        // Create and/or open the database for writing
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//        // consistency of the database.
//        db.beginTransaction();
//        long id = 0;
//        try {
//
//            ContentValues cv = new ContentValues();
//            cv.put(DatabaseStructure.CampTable.CAMPNAME, name);
//            cv.put(DatabaseStructure.CampTable.LOCATION, location);
//            id=db.insert(DatabaseStructure.CampTable.TABLE_NAME,null,cv);
//            db.setTransactionSuccessful();
//            Message.message(ctx, "SUCCESSFULLY INSERTED");
//
//        } catch (Exception e) {
//            Message.message(ctx, "DATA NOT INSERTED");
//        } finally {
//
//            db.endTransaction();
//        }
//        return id;
//    }
//
//    public String getAllData(){
//        SQLiteDatabase db = helper.getWritableDatabase();
//        StringBuffer buffer = new StringBuffer();
//
//        String[] cols = {DatabaseStructure.CampTable.UID,DatabaseStructure.CampTable.CAMPNAME,DatabaseStructure.CampTable.LOCATION};
//        Cursor cursor = db.query(DatabaseStructure.CampTable.TABLE_NAME,cols,null,null,null,null,null);
//        db.beginTransaction();
//
//        try {
//            while (cursor.moveToNext()){
//
//               int cid = cursor.getInt(0);
//                String name = cursor.getString(1);
//                String camploc = cursor.getString(2);
//                buffer.append(cid+ " "+name+" "+camploc+"\n");
//
//            }
//
//        } catch (Exception e) {
//            Message.message(ctx, "COULD NOT APPEND TO BUFFER");
//        } finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//                db.endTransaction();
//            }
//        }
//        return buffer.toString();
//    }
//
//    static class DBHelper extends SQLiteOpenHelper{
//        private static final int DB_VERSION = 1111;
//        //private static String DB_PATH = "";
//        SQLiteDatabase db;
//        Context context;
//
//        /**
//         * TABLE STRINGS
//         */
//        private static final String TEXT_TYPE = " TEXT";
//        private static final String INTEGER_TYPE = " INTEGER  ";
//        private static final String COMMA = ", ";
//        private static final String P_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT,  ";
//        private static final String CREATE_CAMPTABLE = "CREATE TABLE  "
//                +   DatabaseStructure.CampTable.TABLE_NAME + "("
//                +   DatabaseStructure.CampTable.UID + P_KEY
//                +   DatabaseStructure.CampTable.CAMPNAME + TEXT_TYPE + COMMA
//                +   DatabaseStructure.CampTable.LOCATION + TEXT_TYPE + ")";
//
//        private static final String DROP_CAMPTABLE = "DROP TABLE IF EXISTS "+ DatabaseStructure.CampTable.TABLE_NAME;
//        /**
//         * DBHelper Constructor
//         * @param context
//         */
//
//        public DBHelper(Context context) {
//            super(context, DatabaseStructure.DB_NAME, null, DB_VERSION);
//            this.context = context;
//            Message.message(context, "DBHelper CONSTRUCTOR CALLED");
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//
//            try {
//                db.execSQL(CREATE_CAMPTABLE);
//                this.db = db;
//                Message.message(context, "DB TABLE CREATED");
//            } catch (SQLException e) {
//                Message.message(context, " TABLE NOT CREATED" + e);
//            }
//
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            try {
//                db.execSQL(DROP_CAMPTABLE);
//                onCreate(db);
//            } catch (SQLException e) {
//                Message.message(context, "DB TABLE UPGRADED"+e);
//            }
//        }
//
//
//    }



}

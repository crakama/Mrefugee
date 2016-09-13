package com.crakama.refugee.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 0001;
    /**
     * TABLE STRINGS
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA = ", ";

    /**
     * SQL STATEMENT
     */
    private static final String CREATE_CAMPTABLE = "CREATE TABLE  "
            +   DatabaseStructure.CampTable.TABLE_NAME + "("
            +   DatabaseStructure.CampTable.CAMPNAME + TEXT_TYPE + COMMA
            +   DatabaseStructure.CampTable.LOCATION + TEXT_TYPE + ")";

    private static final String DROP_CAMPTABLE = "DROP TABLE IF EXISTS "+ DatabaseStructure.CampTable.TABLE_NAME;
    /**
     * DBHelper Constructor
     * @param context
     */

    public DBOpenHelper(Context context) {
        super(context, DatabaseStructure.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CAMPTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(DROP_CAMPTABLE);
        onCreate(db);
    }
}

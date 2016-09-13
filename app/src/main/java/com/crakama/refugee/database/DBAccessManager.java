package com.crakama.refugee.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.crakama.refugee.DadaabCamp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DBAccessManager {

    /**
     * Singleton pattern
     */
    private static DBAccessManager isInstance = null;
    ArrayList<DadaabCamp> av = new ArrayList<DadaabCamp>();

    /**
     * Get an Insatnce of DB Object
     *
     * @return instance
     * @param //s
     * @param //toString
     */
    public static DBAccessManager getsInstance() {
        if (isInstance == null) {
            isInstance = new DBAccessManager();
        }
        return isInstance;
    }

    /**
     * saveCampData(String campname, String loc)
     *
     * @param //camplist
     * @return
     */

    public boolean saveCampData(Context context, List<DadaabCamp> av) {
        try {
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();
            db.beginTransaction();
            for (DadaabCamp camp : av) {
                ContentValues cv = new ContentValues();
                cv.put(DatabaseStructure.CampTable.CAMPNAME, camp.getName());
                cv.put(DatabaseStructure.CampTable.LOCATION, camp.getDescription());

                db.insert(DatabaseStructure.CampTable.TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        } catch (Exception e) {
            return false;
        }


        return true;
    }
}

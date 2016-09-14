package com.crakama.refugee.database;

/**
 * Created by cate.rakama@gmail.com on 9/12/2016.
 */
public class DatabaseStructure {
    public static final String DB_NAME = "refdb.db";
    public abstract class CampTable{

        public static final String TABLE_NAME = "camptable ";

        public static final String UID = "_id";
        public static final String CAMPNAME = "title";
        public static final String LOCATION = "location";


    }

}

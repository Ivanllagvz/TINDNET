package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                    UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserContract.UserEntry.COLUMN_NAME + " TEXT," +
                    UserContract.UserEntry.COLUMN_DNI + " TEXT," +
                    UserContract.UserEntry.COLUMN_PHONE + " TEXT," +
                    UserContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                    UserContract.UserEntry.COLUMN_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

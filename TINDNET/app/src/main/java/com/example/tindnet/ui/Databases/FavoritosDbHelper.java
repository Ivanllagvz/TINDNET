package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritosDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Favoritos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavoritosContract.FavoritosEntry.TABLE_NAME + " (" +
                    FavoritosContract.FavoritosEntry._ID + " INTEGER PRIMARY KEY," +
                    FavoritosContract.FavoritosEntry.COLUMN_NOMBRE + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_NUMERO + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_EMAIL + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_PASSWORD + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_DESCRIPCION + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_TELEFONO + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_WEB + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_SECTOR + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_RAZON_SOCIAL + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_HORARIOS + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_DOCUMENTO_URI + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_2 + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_3 + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_4 + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_5 + " TEXT," +
                    FavoritosContract.FavoritosEntry.COLUMN_LOGO_URI + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavoritosContract.FavoritosEntry.TABLE_NAME;

    public FavoritosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
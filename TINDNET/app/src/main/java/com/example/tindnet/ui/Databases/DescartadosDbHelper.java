package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DescartadosDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Descartados.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DescartadosContract.DescartadosEntry.TABLE_NAME + " (" +
                    DescartadosContract.DescartadosEntry._ID + " INTEGER PRIMARY KEY," +
                    DescartadosContract.DescartadosEntry.COLUMN_NOMBRE + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_NUMERO + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_EMAIL + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_PASSWORD + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_DESCRIPCION + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_TELEFONO + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_WEB + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_SECTOR + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_RAZON_SOCIAL + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_HORARIOS + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_DOCUMENTO_URI + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_2 + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_3 + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_4 + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_5 + " TEXT," +
                    DescartadosContract.DescartadosEntry.COLUMN_LOGO_URI + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DescartadosContract.DescartadosEntry.TABLE_NAME;

    public DescartadosDbHelper(Context context) {
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
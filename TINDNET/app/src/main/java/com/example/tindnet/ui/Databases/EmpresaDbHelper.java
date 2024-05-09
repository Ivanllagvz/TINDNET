package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmpresaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Empresa.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EmpresaContract.EmpresaEntry.TABLE_NAME + " (" +
                    EmpresaContract.EmpresaEntry._ID + " INTEGER PRIMARY KEY," +
                    EmpresaContract.EmpresaEntry.COLUMN_NOMBRE + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_NUMERO + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_EMAIL + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_DESCRIPCION + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_WEB + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_SECTOR + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_RAZON_SOCIAL + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_HORARIOS + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_DOCUMENTO_URI + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_IMAGEN_URI + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_LOGO_URI + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EmpresaContract.EmpresaEntry.TABLE_NAME;

    public EmpresaDbHelper(Context context) {
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

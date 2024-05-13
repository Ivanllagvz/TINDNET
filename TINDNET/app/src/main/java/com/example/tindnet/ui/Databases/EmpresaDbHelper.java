package com.example.tindnet.ui.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Empresa.db";
    private static final int DATABASE_VERSION = 2;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EmpresaContract.EmpresaEntry.TABLE_NAME + " (" +
                    EmpresaContract.EmpresaEntry._ID + " INTEGER PRIMARY KEY," +
                    EmpresaContract.EmpresaEntry.COLUMN_NOMBRE + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_NUMERO + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_EMAIL + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_PASSWORD + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_DESCRIPCION + " TEXT," +
                    EmpresaContract.EmpresaEntry.COLUMN_TELEFONO + " TEXT," +
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


    public boolean checkEmpresaCredentials(String email, String password, String fiscalId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                EmpresaContract.EmpresaEntry._ID
        };

        String selection = EmpresaContract.EmpresaEntry.COLUMN_EMAIL + " = ? AND " +
                EmpresaContract.EmpresaEntry.COLUMN_PASSWORD + " = ? AND " +
                EmpresaContract.EmpresaEntry.COLUMN_NUMERO + " = ?";

        String[] selectionArgs = {email, password, fiscalId};

        Cursor cursor = db.query(
                EmpresaContract.EmpresaEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                EmpresaContract.EmpresaEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_NOMBRE));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_DESCRIPCION));
            String web = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_WEB));
            String sector = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_SECTOR));
            String razonSocial = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_RAZON_SOCIAL));
            String horarios = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_HORARIOS));
            String imagenUri = cursor.getString(cursor.getColumnIndexOrThrow(EmpresaContract.EmpresaEntry.COLUMN_IMAGEN_URI));
            Empresa empresa = new Empresa(nombre, descripcion, web, sector, razonSocial, horarios, imagenUri);
            empresas.add(empresa);
        }
        cursor.close();
        return empresas;
    }
}

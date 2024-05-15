package com.example.tindnet.ui.Databases;

import android.provider.BaseColumns;

public final class EmpresaContract {

    private EmpresaContract() {}

    public static class EmpresaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Empresa";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_NUMERO = "numero";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_DESCRIPCION = "descripcion";
        public static final String COLUMN_TELEFONO = "telefono";
        public static final String COLUMN_WEB = "web";
        public static final String COLUMN_SECTOR = "sector";
        public static final String COLUMN_RAZON_SOCIAL = "razon_social";
        public static final String COLUMN_HORARIOS = "horarios";
        public static final String COLUMN_DOCUMENTO_URI = "documento_uri";
        public static final String COLUMN_IMAGEN_URI = "imagen_uri";
        public static final String COLUMN_IMAGEN_URI_2 = "imagen_uri_2";
        public static final String COLUMN_IMAGEN_URI_3 = "imagen_uri_3";
        public static final String COLUMN_IMAGEN_URI_4 = "imagen_uri_4";
        public static final String COLUMN_IMAGEN_URI_5 = "imagen_uri_5";
        public static final String COLUMN_LOGO_URI = "logo_uri";
    }
}

package com.example.tindnet.ui.Databases;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract() {}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DNI = "dni";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
    }
}

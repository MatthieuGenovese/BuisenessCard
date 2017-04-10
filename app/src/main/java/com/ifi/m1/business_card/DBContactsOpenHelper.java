package com.ifi.m1.business_card;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matthieu on 10/04/2017.
 */

public class DBContactsOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "test";
    public static final String COLUMN_NAME_KEY = "id";
    public static final String COLUMN_NAME_NOM = "nom";
    public static final String COLUMN_NAME_PRENOM= "prenom";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PROFESSION = "profession";
    public static final String COLUMN_NAME_ADRESSE = "adresse";
    public static final String COLUMN_NAME_TEL = "tel";
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
            COLUMN_NAME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_NOM + " TEXT, " +
            COLUMN_NAME_PRENOM + " TEXT, " +
            COLUMN_NAME_EMAIL + " TEXT, " +
            COLUMN_NAME_TEL + " TEXT, " +
            COLUMN_NAME_ADRESSE + " TEXT, " +
            COLUMN_NAME_PROFESSION + " TEXT);";
    public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public DBContactsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}


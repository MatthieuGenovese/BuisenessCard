package com.ifi.m1.business_card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Matthieu on 10/04/2017.
 */
public class ContactsAdapter extends ContactsBase{
    public static final String TABLE_NAME = "test";
    public static final String COLUMN_NAME_KEY = "_id";
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
    public Context pContext;

    ContactsAdapter(Context pContext) {
        super(pContext);
        this.pContext = pContext;
    }

    public Cursor getAllDatas(){
        super.open();
        return super.getDb().rawQuery("SELECT id as _id, nom, prenom, email, profession, adresse, tel FROM " + TABLE_NAME, new String[]{});
    }

    public void ajouter(Contact c) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NOM, c.getNom());
        values.put(COLUMN_NAME_PRENOM, c.getPrenom());
        values.put(COLUMN_NAME_ADRESSE, c.getAdresse());
        values.put(COLUMN_NAME_EMAIL, c.getEmail());
        values.put(COLUMN_NAME_PROFESSION, c.getProfession());
        values.put(COLUMN_NAME_TEL, c.getTel());
        super.getDb().insert(TABLE_NAME,null, values);
    }


    public void supprimer(long id) {
    }

    public void modifier() {
    }

    public void selectionner(long id) {
    }
}

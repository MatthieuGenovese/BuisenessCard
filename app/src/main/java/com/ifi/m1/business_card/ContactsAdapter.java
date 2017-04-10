package com.ifi.m1.business_card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Matthieu on 10/04/2017.
 */
public class ContactsAdapter extends ContactsBase{
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

    ContactsAdapter(Context pContext) {
        super(pContext);
    }

    public void ajouter() {
        super.open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NOM, "A");
        values.put(COLUMN_NAME_PRENOM, "Marc");
        values.put(COLUMN_NAME_ADRESSE, "6 Avenue Doniol");
        values.put(COLUMN_NAME_EMAIL, "matthieu.genovese@gmail.com");
        values.put(COLUMN_NAME_PROFESSION, "Chomeur");
        values.put(COLUMN_NAME_TEL, "0662656889");
        super.getDb().insert(TABLE_NAME,null, values);
        Cursor c =super.getDb().rawQuery("SELECT * FROM " + TABLE_NAME, new String[]{});
        System.out.println("youhouuuuu");
        while(c.moveToNext()){
            int id = c.getInt(0);
            String nom = c.getString(1);
            String prenom = c.getString(2);
            String email = c.getString(3);
            String tel = c.getString(4);
            String adresse = c.getString(5);
            String profession = c.getString(6);
            System.out.println(id + " " + nom + " " + prenom + " " + email + " " + tel + " " + adresse + " " + profession);

        }
        c.close();
    }


    public void supprimer(long id) {
    }

    public void modifier() {
    }

    public void selectionner(long id) {
    }
}

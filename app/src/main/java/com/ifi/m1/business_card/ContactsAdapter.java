package com.ifi.m1.business_card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Matthieu on 10/04/2017.
 */
public class ContactsAdapter extends ContactsBase {
    public static final String TABLE_NAME = "test";
    public static final String COLUMN_NAME_KEY = "_id";
    public static final String COLUMN_NAME_NOM = "nom";
    public static final String COLUMN_NAME_PRENOM = "prenom";
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

    public Cursor getAllDatas() {
        super.open();
        Cursor c = super.getDb().rawQuery("SELECT _id, nom, tel, email, adresse FROM " + TABLE_NAME, new String[]{});
        return c;
    }

    public void ajouter(Carte c) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NOM, c.getNom());
        values.put(COLUMN_NAME_TEL, c.getTel());
        values.put(COLUMN_NAME_EMAIL, c.getEmail());
        values.put(COLUMN_NAME_ADRESSE, c.getAdresse());
        System.out.println("Ajout de : " + c.getNom() + " , " + c.getTel() + " , " + c.getEmail() + " , " + c.getAdresse());
        super.getDb().insert(TABLE_NAME, null, values);
    }


    public void deleteAll() {
        Cursor c = super.getDb().rawQuery("DELETE FROM " + TABLE_NAME, new String[]{});
        while (c.moveToNext()) {
            System.out.println("données supprimée !");
        }
        c.close();
    }

    public void deleteOne(String nom) {
        Cursor c = super.getDb().rawQuery("DELETE FROM " + TABLE_NAME + " WHERE nom = '" + nom + "';", new String[]{});
        while (c.moveToNext()) {
            System.out.println(nom + " a été supprimé !");
        }
        c.close();
    }

    public void modifier() {
    }

    public Carte selectionner(String nom) {
        Cursor cursor = super.getDb().rawQuery("SELECT nom, tel, email, adresse FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_NOM + " = '" + nom + "';", new String[]{});
        Carte c = new Carte("", "");
        while (cursor.moveToNext()) {
            c = new Carte(cursor.getString(0), cursor.getString(1));
            c.setEmail(cursor.getString(2));
            System.out.println("EMAIL:" + cursor.getString(2));
            c.setAdresse(cursor.getString(3));
        }
        cursor.close();
        return c;
    }

    public int getNbContacts() {
        super.open();
        Cursor c = super.getDb().rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, new String[]{});
        int result = 0;
        while (c.moveToNext()) {
            result = c.getInt(0);
        }
        return result;
    }

    public boolean existContact(String nom) {
        Cursor cursor = super.getDb().rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_NOM + " = '" + nom + "';", new String[]{});

        int result = 0;
        while (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();

        return result != 0;
    }
}

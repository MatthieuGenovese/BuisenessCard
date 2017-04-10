package com.ifi.m1.business_card;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matthieu on 10/04/2017.
 */

public abstract class ContactsBase{
    protected final static int VERSION = 1;
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected DBContactsOpenHelper mHandler = null;

    public ContactsBase(Context pContext) {
        this.mHandler = new DBContactsOpenHelper(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

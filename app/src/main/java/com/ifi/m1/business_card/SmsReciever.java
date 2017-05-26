package com.ifi.m1.business_card;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SmsReciever  {
    private MainActivity act;

    public SmsReciever(MainActivity act) {
        this.act = act;
    }

    public List<String> getAllSmsFromProvider() {
        List<String> lstSms = new ArrayList<String>();
        ContentResolver cr = act.getContentResolver();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                new String[] { Telephony.Sms.Inbox.BODY }, // Select body text
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                lstSms.add(c.getString(0));
                c.moveToNext();
            }
        } else {
            throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return lstSms;
    }
}

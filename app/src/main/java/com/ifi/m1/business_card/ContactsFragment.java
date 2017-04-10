package com.ifi.m1.business_card;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactsFragment extends Fragment {
    View vContacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vContacts = inflater.inflate(R.layout.contacts_layout, container, false);
        ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getActivity().getApplicationContext(),android.R.layout.simple_list_item_2, db.getAllDatas(), new String[]{db.COLUMN_NAME_NOM , db.COLUMN_NAME_PRENOM}, new int[]{android.R.id.text1, android.R.id.text2} );
        ListView list = (ListView)vContacts.findViewById(R.id.listContacts);

        list.setAdapter(adapter);
        return vContacts;
    }
}

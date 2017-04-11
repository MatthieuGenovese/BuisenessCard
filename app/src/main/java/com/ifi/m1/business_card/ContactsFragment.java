package com.ifi.m1.business_card;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactsFragment extends ListFragment {
    View vContacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vContacts = inflater.inflate(R.layout.contacts_layout, container, false);
        return vContacts;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        final ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
        Contact c = new Contact("Froment", "Jeremy", "Nice", "jeremy@jeremy.jeremy", "0662656869", "developper");
        Contact c2 = new Contact("Genovese", "Matthieu", "Antibes", "matthieu@matthieu.matthieu", "0662658578", "developper");
        db.deleteAll();
        db.ajouter(c2);
        db.ajouter(c);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getActivity().getApplicationContext(),android.R.layout.simple_list_item_2, db.getAllDatas(), new String[]{db.COLUMN_NAME_NOM , db.COLUMN_NAME_PRENOM}, new int[]{android.R.id.text1, android.R.id.text2} ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
                textView.setTextColor(Color.BLACK);
                textView2.setTextColor(Color.BLACK);
                return view;
            }
        };
        //final ListView list = (ListView) vContacts.findViewById(android.R.id.list);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteCursor c = (SQLiteCursor) getListView().getItemAtPosition(1);
                System.out.println(adapterView.toString());
                System.out.println(c.getString(1) + c.getString(2));
                System.out.println(db.selectionner(c.getString(1), c.getString(2)));
            }
        });
        setListAdapter(adapter);
        //return vContacts;
    }
}

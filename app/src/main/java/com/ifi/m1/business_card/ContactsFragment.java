package com.ifi.m1.business_card;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsFragment extends ListFragment {
    View vContacts;
    private ArrayList<String> noms = new ArrayList<>();
    private ArrayList<String> prenoms = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vContacts = inflater.inflate(R.layout.contacts_layout, container, false);
        return vContacts;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        final ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
        TextView header = new TextView(this.getActivity());
        header.setText("Mes contacts");
        getListView().addHeaderView(header);
        Contact c = new Contact("Froment", "Jeremy", "Nice", "jeremy@jeremy.jeremy", "0662656869", "developper");
        Contact c2 = new Contact("Genovese", "Matthieu", "Antibes", "matthieu@matthieu.matthieu", "0662658578", "developper");
        db.deleteAll();
        db.ajouter(c2);
        db.ajouter(c);
        Cursor cursor = db.getAllDatas();
        getListView().setTextFilterEnabled(true);
        setListAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, results){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        });
        while(cursor.moveToNext()){
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            results.add(nom + " " + prenom);
            noms.add(nom);
            prenoms.add(prenom);
        }
        cursor.close();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(db.selectionner(noms.get(i-1), prenoms.get(i-1)));
            }
        });
    }
}

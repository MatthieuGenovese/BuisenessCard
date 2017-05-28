package com.ifi.m1.business_card;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsFragment extends ListFragment {
    View vContacts;
    ViewGroup container;
    LayoutInflater inflater;
    private ArrayList<String> results = new ArrayList<>();
    Button contactsList;
    TextView noContacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        vContacts = inflater.inflate(R.layout.contacts_layout, container, false);
        return vContacts;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());

        contactsList = (Button) this.getActivity().findViewById(R.id.addFromContacts);
        contactsList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
        initListContacts(db);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        String id="", cName ="", cNumber = "", cEmail = "", cAdresse = "";

        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (1):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor = this.getActivity().managedQuery(contactData, null, null, null, null);

                    if (cursor.moveToFirst()) {
                        id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        cName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {

                            // On récupère le numéro de tel
                            Cursor phoneCursor = this.getActivity().getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);
                            phoneCursor.moveToFirst();
                            cNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phoneCursor.close();


                            // On récupère l'adresse email
                            Cursor emailCursor = this.getActivity().getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id,
                                    null, null);
                            emailCursor.moveToFirst();
                            try {
                                cEmail = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                                emailCursor.close();
                            }
                            catch(Exception e){}

                            // On récupère l'adresse postale
                            String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                            String[] addrWhereParams = new String[]{id, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
                            Cursor adresseCursor = this.getActivity().getContentResolver().query(
                                    ContactsContract.Data.CONTENT_URI,
                                    null, addrWhere,
                                    addrWhereParams, null);
                            while(adresseCursor.moveToNext()) {
                                cAdresse = adresseCursor.getString(adresseCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                        }
                        adresseCursor.close();

                            Carte c = new Carte(cName, cNumber);
                            c.setContactID(id);
                            c.setEmail(cEmail);
                            c.setAdresse(cAdresse);

                            final ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
                            if (!db.existContact(cName)) {
                                //db.ajouter(c);
                                db.ajouterFromTel(c);
                                initListContacts(db);
                            }
                        }
                    }
                }
                break;
        }
    }

    public void initListContacts(final ContactsAdapter db) {
        results = new ArrayList<>();
        Cursor cursor = db.getAllDatas();
        getListView().setTextFilterEnabled(true);
        setListAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, results) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        });
        while (cursor.moveToNext()) {
            if(cursor.getString(1).equalsIgnoreCase("-1")) {
                String nomS = cursor.getString(2);
                results.add(nomS);
            }
            else{
                // On récupère le numéro de tel
                Cursor phoneCursor = this.getActivity().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + cursor.getString(1),
                        null, null);
                phoneCursor.moveToFirst();
                String nomS = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneCursor.close();
                results.add(nomS);
            }
        }

        noContacts = (TextView) vContacts.findViewById(R.id.noContact);
        if (results.isEmpty()) {
            getListView().setVisibility(View.INVISIBLE);
            noContacts.setVisibility(View.VISIBLE);
        } else {
            getListView().setVisibility(View.VISIBLE);
            noContacts.setVisibility(View.INVISIBLE);
        }

        cursor.close();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String line = (String) getListView().getItemAtPosition(i);
                Carte card = db.selectionner(line);
                if(card.getContactID().equalsIgnoreCase("-1")) {
                    System.out.println("je suis la fdp");
                    ((MainActivity) getActivity()).selectedNom = card.getNom();
                    ((MainActivity) getActivity()).selectedAdresse = card.getAdresse();
                    ((MainActivity) getActivity()).selectedEmail = card.getEmail();
                    ((MainActivity) getActivity()).selectedTelephone = card.getTel();
                }
                else{
                    // On récupère le numéro de tel
                    String cNumber ="",cEmail="",cAdresse="",cName="";
                    Cursor nameCursor = getActivity().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + card.getContactID(),
                            null, null);
                   nameCursor.moveToFirst();
                    cName = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    Cursor phoneCursor = getActivity().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + card.getContactID(),
                            null, null);
                    phoneCursor.moveToFirst();
                    cNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneCursor.close();


                    // On récupère l'adresse email
                    Cursor emailCursor = getActivity().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + card.getContactID(),
                            null, null);
                    emailCursor.moveToFirst();
                    try {
                        cEmail = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailCursor.close();
                    }
                    catch(Exception e){}

                    // On récupère l'adresse postale
                    String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] addrWhereParams = new String[]{card.getContactID(), ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
                    Cursor adresseCursor = getActivity().getContentResolver().query(
                            ContactsContract.Data.CONTENT_URI,
                            null, addrWhere,
                            addrWhereParams, null);
                    while(adresseCursor.moveToNext()) {
                        cAdresse = adresseCursor.getString(adresseCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                    }
                    adresseCursor.close();
                    ((MainActivity) getActivity()).selectedNom = cName;
                    ((MainActivity) getActivity()).selectedAdresse = cAdresse;
                    ((MainActivity) getActivity()).selectedEmail = cEmail;
                    ((MainActivity) getActivity()).selectedTelephone = cNumber;
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, new OneContactFragment())
                        .commit();
            }
        });
    }
}

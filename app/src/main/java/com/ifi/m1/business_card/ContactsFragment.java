package com.ifi.m1.business_card;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    Button contactsList, generateCard;
    EditText nomAffichageContacts;
    TextView nom,prenom,numero, emailAffichageContacts, adresseAffichageContacts, professionAffichageContacts, telAffichageContacts;
    String nomString, prenomString, numeroString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        vContacts = inflater.inflate(R.layout.contacts_layout, container, false);
        return vContacts;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        final ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
        contactsList = (Button) this.getActivity().findViewById(R.id.addFromContacts);
        generateCard = (Button) this.getActivity().findViewById(R.id.generateCard);
        nom = (TextView)this.getActivity().findViewById(R.id.textNom);
        prenom = (TextView)this.getActivity().findViewById(R.id.textPrenom);
        numero = (TextView)this.getActivity().findViewById(R.id.textPhoneNumber);
        nomAffichageContacts = (EditText) getActivity().findViewById(R.id.editTextNom);
        emailAffichageContacts = (TextView) this.getActivity().findViewById(R.id.editTextEmail);
        //professionAffichageContacts = (TextView) this.getActivity().findViewById(R.id.ed);
        adresseAffichageContacts = (TextView) this.getActivity().findViewById(R.id.editTextVille);
        telAffichageContacts = (TextView) this.getActivity().findViewById(R.id.editTextPhone);

        contactsList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
        generateCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Carte c = new Carte(nomString, numeroString);
                if(((MainActivity)getActivity()).getPreferencesAdresse()){

                    System.out.println("ACTIVER");
                }
                db.ajouter(c);
                generateCard.setVisibility(View.INVISIBLE);
            }
        });
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
            String nomS = cursor.getString(1);
            String numeroS = cursor.getString(6);
            results.add(nomS + " " + numeroS);
        }
        cursor.close();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String line = (String) getListView().getItemAtPosition(i);
                String[] decompose = line.split(" ");
                getActivity().setContentView(R.layout.profil_layout);
                Carte card = db.selectionner(decompose[0], decompose[1]);
                System.out.println(card.getNom());
                getActivity().getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.profil, new OneContactFragment())
                        .addToBackStack(null)
                        .commit();
                //adresseAffichageContacts.setText(card.getAdresse());
                //emailAffichageContacts.setText(card.getEmail());
                //telAffichageContacts.setText(card.getTel());
                System.out.println(db.selectionner(decompose[0], decompose[1]));
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (1) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor =  this.getActivity().managedQuery(contactData, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        String id =cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        String hasPhone =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = this.getActivity().getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                            String cName = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            nom.setText(nom.getText() + cName);
                            numero.setText(numero.getText() + cNumber);
                            nomString = cName;
                            numeroString = cNumber;
                            generateCard.setVisibility(View.VISIBLE);
                        }
                    }
                }
                break;
        }
    }
}

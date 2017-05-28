package com.ifi.m1.business_card;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.telephony.SmsManager;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class OneContactFragment extends Fragment {
    View vContact;
    Button deleteContact, sendCard, editContact, validChangement;
    EditText txtNom, txtAdresse, txtEmail, txtTel;
    String nomS = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vContact = inflater.inflate(R.layout.contact_layout, container, false);

        txtNom = (EditText) vContact.findViewById(R.id.editTextNom);
        txtNom.setText(((MainActivity) getActivity()).selectedNom);
        txtNom.setEnabled(false);

        txtAdresse = (EditText) vContact.findViewById(R.id.editTextVille);
        txtAdresse.setText(((MainActivity) getActivity()).selectedAdresse);
        txtAdresse.setEnabled(false);

        txtEmail = (EditText) vContact.findViewById(R.id.editTextEmail);
        txtEmail.setText(((MainActivity) getActivity()).selectedEmail);
        txtEmail.setEnabled(false);

        txtTel = (EditText) vContact.findViewById(R.id.editTextPhone);
        txtTel.setText(((MainActivity) getActivity()).selectedTelephone);
        txtTel.setEnabled(false);

        return vContact;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        deleteContact = (Button) this.getActivity().findViewById(R.id.deleteContact);
        deleteContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ContactsAdapter db = new ContactsAdapter(getActivity().getApplicationContext());
                db.deleteOne(((MainActivity) getActivity()).selectedNom);

                ((MainActivity) getActivity()).selectedNom = "";
                ((MainActivity) getActivity()).selectedAdresse = "";
                ((MainActivity) getActivity()).selectedEmail = "";
                ((MainActivity) getActivity()).selectedTelephone = "";

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, new ContactsFragment())
                        .commit();
            }
        });

        sendCard = (Button) this.getActivity().findViewById(R.id.sendCard);
        sendCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg = "BCJFMG\nBonjour,\n" +
                        "Voici ma carte de visite : \n" +
                        "- " + ((MainActivity) getActivity()).getProfileNom() + " " + ((MainActivity) getActivity()).getProfilePrenom() + "\n" +
                        "- " + ((MainActivity) getActivity()).getProfileTelephone() + "\n" +
                        "- " + ((MainActivity) getActivity()).getProfileEmail() + "\n" +
                        "- " + ((MainActivity) getActivity()).getProfileVille();

                SmsManager.getDefault().sendTextMessage(((MainActivity) getActivity()).selectedTelephone, null, msg, null, null);
                Toast.makeText(getActivity(), "Carte de visite envoyée !", Toast.LENGTH_SHORT).show();
            }
        });

        editContact = (Button) this.getActivity().findViewById(R.id.editButton);
        editContact.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                nomS = txtNom.getText().toString();
                txtAdresse.setEnabled(true);
                txtNom.setEnabled(true);
                txtEmail.setEnabled(true);
                txtTel.setEnabled(true);
                editContact.setEnabled(false);
                validChangement.setVisibility(View.VISIBLE);
                sendCard.setEnabled(false);
                deleteContact.setEnabled(false);
            }
        });

        validChangement = (Button) this.getActivity().findViewById(R.id.validerChangement);
        validChangement.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                txtAdresse.setEnabled(false);
                txtNom.setEnabled(false);
                txtEmail.setEnabled(false);
                txtTel.setEnabled(false);
                final ContactsAdapter db = new ContactsAdapter(getActivity().getApplicationContext());
                db.modifier(nomS, txtEmail.getText().toString(), txtAdresse.getText().toString(), txtTel.getText().toString(), txtNom.getText().toString());
                editContact.setEnabled(true);
                validChangement.setVisibility(View.INVISIBLE);
                sendCard.setEnabled(true);
                deleteContact.setEnabled(true);
            }
        });
    }
}

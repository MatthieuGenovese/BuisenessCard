package com.ifi.m1.business_card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfilFragment extends Fragment {
    View vProfil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vProfil = inflater.inflate(R.layout.profil_layout, container, false);

        EditText txtNom = (EditText) vProfil.findViewById(R.id.editTextNom);
        txtNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le nom
                System.out.println("Nom: " + s);
            }
        });

        EditText txtPrenom = (EditText) vProfil.findViewById(R.id.editTextPrenom);
        txtPrenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le prénom
                System.out.println("Prénom: " + s);
            }
        });

        EditText txtVille = (EditText) vProfil.findViewById(R.id.editTextVille);
        txtVille.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer la ville
                System.out.println("Ville: " + s);
            }
        });

        EditText txtEmail = (EditText) vProfil.findViewById(R.id.editTextEmail);
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer l'email
                System.out.println("Email: " + s);
            }
        });

        EditText txtTelephone = (EditText) vProfil.findViewById(R.id.editTextPhone);
        txtTelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le téléphone
                System.out.println("Telephone: " + s);
            }
        });

        return vProfil;
    }
}

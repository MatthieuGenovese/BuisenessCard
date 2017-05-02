package com.ifi.m1.business_card;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.OutputStreamWriter;

public class ProfilFragment extends Fragment {
    View vProfil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vProfil = inflater.inflate(R.layout.profil_layout, container, false);

        EditText txtNom = (EditText) vProfil.findViewById(R.id.editTextNom);
        txtNom.setText(((MainActivity) this.getActivity()).getProfileNom());
        txtNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le nom
                ((MainActivity) getActivity()).setProfileNom(String.valueOf(s));
                ecrireConfigProfile();
                ((MainActivity) getActivity()).lireConfigProfile();
            }
        });

        EditText txtPrenom = (EditText) vProfil.findViewById(R.id.editTextPrenom);
        txtPrenom.setText(((MainActivity) this.getActivity()).getProfilePrenom());
        txtPrenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le prénom
                ((MainActivity) getActivity()).setProfilePrenom(String.valueOf(s));
                ecrireConfigProfile();
                ((MainActivity) getActivity()).lireConfigProfile();
            }
        });

        EditText txtVille = (EditText) vProfil.findViewById(R.id.editTextVille);
        txtVille.setText(((MainActivity) this.getActivity()).getProfileVille());
        txtVille.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer la ville
                ((MainActivity) getActivity()).setProfileVille(String.valueOf(s));
                ecrireConfigProfile();
                ((MainActivity) getActivity()).lireConfigProfile();
            }
        });

        EditText txtEmail = (EditText) vProfil.findViewById(R.id.editTextEmail);
        txtEmail.setText(((MainActivity) this.getActivity()).getProfileEmail());
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer l'email
                ((MainActivity) getActivity()).setProfileEmail(String.valueOf(s));
                ecrireConfigProfile();
                ((MainActivity) getActivity()).lireConfigProfile();
            }
        });

        EditText txtTelephone = (EditText) vProfil.findViewById(R.id.editTextPhone);
        txtTelephone.setText(((MainActivity) this.getActivity()).getProfileTelephone());
        txtTelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Changer le téléphone
                ((MainActivity) getActivity()).setProfileTelephone(String.valueOf(s));
                ecrireConfigProfile();
                ((MainActivity) getActivity()).lireConfigProfile();
            }
        });

        return vProfil;
    }

    private void ecrireConfigProfile() {
        OutputStreamWriter osw;
        try {
            osw = new OutputStreamWriter(this.getActivity().openFileOutput("profile.bin", Context.MODE_PRIVATE));
            osw.write(String.valueOf(((MainActivity) getActivity()).getProfileNom()));
            osw.write(";");
            osw.write(String.valueOf(((MainActivity) getActivity()).getProfilePrenom()));
            osw.write(";");
            osw.write(String.valueOf(((MainActivity) getActivity()).getProfileVille()));
            osw.write(";");
            osw.write(String.valueOf(((MainActivity) getActivity()).getProfileEmail()));
            osw.write(";");
            osw.write(String.valueOf(((MainActivity) getActivity()).getProfileTelephone()));
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

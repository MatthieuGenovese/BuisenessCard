package com.ifi.m1.business_card;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class PreferencesFragment extends Fragment {
    View vPreferences;
    CheckBox prenom,email,adresse,profession;
    boolean prenomB,adresseB,emailB,professionB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vPreferences = inflater.inflate(R.layout.preferences_layout, container, false);
        return vPreferences;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        prenomB = ((MainActivity)this.getActivity()).getPrenomB();
        adresseB = ((MainActivity)this.getActivity()).getAdresseB();
        professionB = ((MainActivity)this.getActivity()).getProfessionB();
        emailB = ((MainActivity)this.getActivity()).getEmailB();
        prenom = (CheckBox) this.getActivity().findViewById(R.id.checkPrenom);
        adresse = (CheckBox) this.getActivity().findViewById(R.id.checkAdresse);
        email = (CheckBox) this.getActivity().findViewById(R.id.checkEmail);
        profession = (CheckBox) this.getActivity().findViewById(R.id.checkProfession);
        prenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prenomB = prenom.isChecked();
                ecrireConfig();
                ((MainActivity)getActivity()).lireConfig();
                prenomB = ((MainActivity)getActivity()).getPrenomB();
                adresseB = ((MainActivity)getActivity()).getAdresseB();
                professionB = ((MainActivity)getActivity()).getProfessionB();
                emailB = ((MainActivity)getActivity()).getEmailB();
            }
        });
        adresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adresseB = adresse.isChecked();
                ecrireConfig();
                ((MainActivity)getActivity()).lireConfig();
                prenomB = ((MainActivity)getActivity()).getPrenomB();
                adresseB = ((MainActivity)getActivity()).getAdresseB();
                professionB = ((MainActivity)getActivity()).getProfessionB();
                emailB = ((MainActivity)getActivity()).getEmailB();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailB = email.isChecked();
                ecrireConfig();
                ((MainActivity)getActivity()).lireConfig();
                prenomB = ((MainActivity)getActivity()).getPrenomB();
                adresseB = ((MainActivity)getActivity()).getAdresseB();
                professionB = ((MainActivity)getActivity()).getProfessionB();
                emailB = ((MainActivity)getActivity()).getEmailB();
            }
        });
        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professionB = profession.isChecked();
                ecrireConfig();
                ((MainActivity)getActivity()).lireConfig();
                prenomB = ((MainActivity)getActivity()).getPrenomB();
                adresseB = ((MainActivity)getActivity()).getAdresseB();
                professionB = ((MainActivity)getActivity()).getProfessionB();
                emailB = ((MainActivity)getActivity()).getEmailB();
            }
        });
        if(prenomB){
            prenom.setChecked(true);
        }
        if(adresseB){
            adresse.setChecked(true);
        }
        if(emailB){
            email.setChecked(true);
        }
        if(professionB){
            profession.setChecked(true);
        }


    }

    private void ecrireConfig(){
       OutputStreamWriter osw;
        try {
            osw = new OutputStreamWriter(this.getActivity().openFileOutput("config.bin", Context.MODE_PRIVATE));
            osw.write(String.valueOf(prenomB));
            osw.write(" ");
            osw.write(String.valueOf(adresseB));
            osw.write(" ");
            osw.write(String.valueOf(emailB));
            osw.write(" ");
            osw.write(String.valueOf(professionB));
            osw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

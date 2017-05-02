package com.ifi.m1.business_card;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.io.OutputStreamWriter;

public class PreferencesFragment extends Fragment {
    View vPreferences;
    CheckBox prenom, email, adresse, profession;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vPreferences = inflater.inflate(R.layout.preferences_layout, container, false);
        return vPreferences;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        prenom = (CheckBox) this.getActivity().findViewById(R.id.checkPrenom);
        prenom.setChecked(((MainActivity) getActivity()).getPreferencesPrenom());
        prenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setPreferencesPrenom(prenom.isChecked());
                ecrireConfig();
                ((MainActivity) getActivity()).lireConfig();
            }
        });

        adresse = (CheckBox) this.getActivity().findViewById(R.id.checkAdresse);
        adresse.setChecked(((MainActivity) getActivity()).getPreferencesAdresse());
        adresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setPreferencesAdresse(adresse.isChecked());
                ecrireConfig();
                ((MainActivity) getActivity()).lireConfig();
            }
        });

        email = (CheckBox) this.getActivity().findViewById(R.id.checkEmail);
        email.setChecked(((MainActivity) getActivity()).getPreferencesEmail());
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setPreferencesEmail(email.isChecked());
                ecrireConfig();
                ((MainActivity) getActivity()).lireConfig();
            }
        });

        profession = (CheckBox) this.getActivity().findViewById(R.id.checkProfession);
        profession.setChecked(((MainActivity) getActivity()).getPreferencesProfession());
        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setPreferencesProfession(profession.isChecked());
                ecrireConfig();
                ((MainActivity) getActivity()).lireConfig();
            }
        });
    }

    private void ecrireConfig() {
        OutputStreamWriter osw;
        try {
            osw = new OutputStreamWriter(this.getActivity().openFileOutput("config.bin", Context.MODE_PRIVATE));
            osw.write(String.valueOf(((MainActivity) getActivity()).getPreferencesPrenom()));
            osw.write(" ");
            osw.write(String.valueOf(((MainActivity) getActivity()).getPreferencesAdresse()));
            osw.write(" ");
            osw.write(String.valueOf(((MainActivity) getActivity()).getPreferencesEmail()));
            osw.write(" ");
            osw.write(String.valueOf(((MainActivity) getActivity()).getPreferencesProfession()));
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

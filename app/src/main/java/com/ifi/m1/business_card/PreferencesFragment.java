package com.ifi.m1.business_card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PreferencesFragment extends Fragment {
    View vPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vPreferences = inflater.inflate(R.layout.parameters_layout, container, false);

        Switch switchNotif = (Switch) vPreferences.findViewById(R.id.switch_notifications);
        switchNotif.setChecked(true);
        switchNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Notifications activés ? " + isChecked);
            }
        });

        Switch switchVibration = (Switch) vPreferences.findViewById(R.id.switch_vibration);
        switchVibration.setChecked(true);
        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Vibration activé ? " + isChecked);
            }
        });

        Switch switchSound = (Switch) vPreferences.findViewById(R.id.switch_sound);
        switchSound.setChecked(true);
        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Son activé ? " + isChecked);
            }
        });

        return vPreferences;
    }
}

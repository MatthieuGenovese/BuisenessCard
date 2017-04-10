package com.ifi.m1.business_card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfilFragment extends Fragment {
    View vProfil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vProfil = inflater.inflate(R.layout.profil_layout, container, false);
        return vProfil;
    }
}

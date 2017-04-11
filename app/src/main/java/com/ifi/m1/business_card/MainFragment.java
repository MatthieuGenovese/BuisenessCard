package com.ifi.m1.business_card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {
    View vMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vMain = inflater.inflate(R.layout.main_layout, container, false);
        ContactsAdapter db = new ContactsAdapter(this.getActivity().getApplicationContext());
        TextView textStats = (TextView)vMain.findViewById(R.id.main_stats);
        textStats.setText(Integer.toString(db.getNbContacts()));

        return vMain;
    }
}

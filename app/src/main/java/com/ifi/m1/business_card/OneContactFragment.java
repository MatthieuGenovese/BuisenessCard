package com.ifi.m1.business_card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Matthieu on 08/05/2017.
 */

public class OneContactFragment extends Fragment {
    View vContact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vContact = inflater.inflate(R.layout.profil_layout, container, false);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        EditText txtNom = (EditText) vContact.findViewById(R.id.editTextNom);
        EditText txtAdresse = (EditText) vContact.findViewById(R.id.editTextVille);
        EditText txtEmail = (EditText) vContact.findViewById(R.id.editTextEmail);
        EditText txtTel = (EditText) vContact.findViewById(R.id.editTextPhone);
        return vContact;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}

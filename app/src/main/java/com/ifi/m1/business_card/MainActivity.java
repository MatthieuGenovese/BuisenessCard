package com.ifi.m1.business_card;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public boolean preferencesPrenom = false;
    public boolean preferencesAdresse = false;
    public boolean preferencesEmail = false;
    public boolean preferencesProfession = false;

    private String profileNom = "";
    private String profilePrenom = "";
    private String profileVille = "";
    private String profileEmail = "";
    private String profileTelephone = "";

    public String selectedNom = "";
    public String selectedAdresse = "";
    public String selectedEmail = "";
    public String selectedTelephone = "";

    public boolean getPreferencesPrenom() {
        return preferencesPrenom;
    }

    public void setPreferencesPrenom(boolean preferencesPrenom) {
        this.preferencesPrenom = preferencesPrenom;
    }

    public boolean getPreferencesAdresse() {
        return preferencesAdresse;
    }

    public void setPreferencesAdresse(boolean preferencesAdresse) {
        this.preferencesAdresse = preferencesAdresse;
    }

    public boolean getPreferencesEmail() {
        return preferencesEmail;
    }

    public void setPreferencesEmail(boolean emailB) {
        this.preferencesEmail = emailB;
    }

    public boolean getPreferencesProfession() {
        return preferencesProfession;
    }

    public void setPreferencesProfession(boolean professionB) {
        this.preferencesProfession = professionB;
    }

    public void lireConfig() {

        try {
            InputStream inputStream = openFileInput("config.bin");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String[] config = stringBuilder.toString().split(" ");
                setPreferencesPrenom(Boolean.parseBoolean(config[0]));
                setPreferencesAdresse(Boolean.parseBoolean(config[1]));
                setPreferencesEmail(Boolean.parseBoolean(config[2]));
                setPreferencesProfession(Boolean.parseBoolean(config[3]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void lireConfigProfile() {
        try {
            InputStream inputStream = openFileInput("profile.bin");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String[] config = stringBuilder.toString().split(";");
                setProfileNom(config[0]);
                setProfilePrenom(config[1]);
                setProfileVille(config[2]);
                setProfileEmail(config[3]);
                setProfileTelephone(config[4]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lireConfig();
        lireConfigProfile();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_profil) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, new ProfilFragment())
                    .commit();
        } else if (id == R.id.nav_parametres) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, new PreferencesFragment())
                    .commit();
        } else if (id == R.id.nav_contact) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, new ContactsFragment())
                    .commit();
        } else if (id == R.id.nav_main) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, new MainFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getProfileNom() {
        return profileNom;
    }

    public void setProfileNom(String profileNom) {
        this.profileNom = profileNom;
    }

    public String getProfilePrenom() {
        return profilePrenom;
    }

    public void setProfilePrenom(String profilePrenom) {
        this.profilePrenom = profilePrenom;
    }

    public String getProfileVille() {
        return profileVille;
    }

    public void setProfileVille(String profileVille) {
        this.profileVille = profileVille;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public String getProfileTelephone() {
        return profileTelephone;
    }

    public void setProfileTelephone(String profileTelephone) {
        this.profileTelephone = profileTelephone;
    }
}

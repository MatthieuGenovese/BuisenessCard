package com.ifi.m1.business_card;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Matthieu on 27/05/2017.
 */

public class FileManager {
    private InputStream inputStream;
    public FileManager(InputStream is){
        this.inputStream = is;
    }

   /* public void lireConfig() {

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

    }*/

    public String[] lireConfigProfile() {
        String[] config = new String[10];
        try {
            //InputStream inputStream = openFileInput("profile.bin");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String[] config2 = stringBuilder.toString().split(";");
                return config2;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return config;
    }

}

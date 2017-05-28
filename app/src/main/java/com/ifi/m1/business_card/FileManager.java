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

    public String[] lireConfigProfile() {
        String[] config = new String[10];
        try {
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

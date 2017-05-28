package com.ifi.m1.business_card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 26/05/2017.
 */

public class ExtractCard {

    public List<Carte> extraireFromString(List<String> liste){
        List<Carte> resultat = new ArrayList<>();
        for(int i = 0; i < liste.size(); i++){
            String[] result = liste.get(i).split("\n");
            if(result[0].equalsIgnoreCase("BCJFMG")){
                String nom = "";
                String ville = "";
                String tel = "";
                String email = "";
                String[] nomS = result[3].split(" ");
                String[] telS = result[4].split(" ");
                String[] emailS = result[5].split(" ");
                String[] villeS = result[6].split(" ");
                if(nomS.length > 1){
                    if(nomS.length > 2) {
                        nom = nomS[1] + " " + nomS[2];
                    }
                    else{
                        nom = nomS[1];
                    }
                }
                if(telS.length > 1){
                    tel = telS[1];
                }
                if(emailS.length > 1){
                    email = emailS[1];
                }
                if(villeS.length > 1){
                    ville = villeS[1];
                }
                Carte res = new Carte(nom,tel);
                res.setAdresse(ville);
                res.setEmail(email);
                resultat.add(res);
                System.out.println(res);
            }
        }
        liste.clear();
        return resultat;
    }
}

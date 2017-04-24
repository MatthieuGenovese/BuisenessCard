package com.ifi.m1.business_card;

/**
 * Created by Matthieu on 10/04/2017.
 */

public class Carte {
    private String nom,prenom,adresse,email,tel,profession;

    public Carte(String nom,String tel){
        this.nom = nom;
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String toString(){
        return nom + prenom + adresse + email  + tel + profession;
    }
}

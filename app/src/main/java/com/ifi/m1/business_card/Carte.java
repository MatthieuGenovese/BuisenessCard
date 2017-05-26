package com.ifi.m1.business_card;

public class Carte {
    private String nom, adresse, email, tel, profession;

    public Carte(String nom, String tel){
        this.nom = nom;
        this.tel = tel;
    }

    public String getNom() {
        return this.nom;
    }

    public String getTel() {
        return this.tel;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String toString(){
        return this.nom + " "+this.adresse + " "+ this.email  + " " +this.tel + " " +this.profession;
    }
}

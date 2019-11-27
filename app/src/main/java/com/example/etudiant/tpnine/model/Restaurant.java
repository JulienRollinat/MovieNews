package com.example.etudiant.tpnine.model;

public class Restaurant {
    private float id;
    private String nom;
    private String photo;
    private float lat;
    private float lng;
    private String tel;
    private String email;
    private String description;
    private String adresse;
    private String ville;
    private float cp;


    // Getter Methods
    public float getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPhoto() {
        return photo;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public float getCp() {
        return cp;
    }

    // Setter Methods
    public void setId(float id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCp(float cp) {
        this.cp = cp;
    }
}
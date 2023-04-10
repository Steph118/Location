package com.example.livedataxample.classes;

import androidx.databinding.DataBindingUtil;

import java.io.Serializable;
public class Agence implements Serializable {
    private String libelle;
    private String localite;
    private String telephone;
    private String email;
    private Double latitude;
    private Double longitude;
    private Sfd sfd;
    public Agence() {
    }
    public Agence(String libelle, String localite, String telephone,
                  String email, double latitude, double longitude, Sfd sfd) {
        this.libelle = libelle;
        this.localite = localite;
        this.telephone = telephone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sfd = sfd;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getLocalite() {
        return localite;
    }
    public void setLocalite(String localite) {
        this.localite = localite;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return "Agence{" +
                "libelle='" + libelle + '\'' +
                ", localite='" + localite + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
    public Sfd getSfd() {
        return sfd;
    }
    public void setSfd(Sfd sfd) {
        this.sfd = sfd;
    }
}

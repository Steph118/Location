package com.example.livedataxample.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "personnes_infos",
        indices = {@Index(value = "email", unique = true ),
                @Index(value = "telephone",unique = true)})
public class PersonneInfos implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "prenom")
    private String prenom;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "nationalite")
    private Pays nationalite;
    @ColumnInfo(name = "localite")
    private Localite localite;
    @ColumnInfo(name = "telephone")
    private String telephone;

    public PersonneInfos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pays getNationalite() {
        return nationalite;
    }

    public void setNationalite(Pays nationalite) {
        this.nationalite = nationalite;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "PersonneInfos{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", nationalite=" + nationalite +
                ", localite=" + localite +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

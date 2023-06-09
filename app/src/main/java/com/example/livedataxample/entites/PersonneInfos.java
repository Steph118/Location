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
    private long id;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "prenom")
    private String prenom;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "nationalite_pays_id")
    private long nationalite;
    @ColumnInfo(name = "localite_id")
    private long localite;
    @ColumnInfo(name = "telephone")
    private String telephone;

    public PersonneInfos() {
    }

    public PersonneInfos(String nom, String prenom, int age,
                         String email, long nationalite,
                         long localite, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.nationalite = nationalite;
        this.localite = localite;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getNationalite() {
        return nationalite;
    }

    public void setNationalite(long nationalite) {
        this.nationalite = nationalite;
    }

    public long getLocalite() {
        return localite;
    }

    public void setLocalite(long localite) {
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

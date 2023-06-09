package com.example.livedataxample.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "villes")
public class Ville implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "libelle")
    private String libelle;
    @ColumnInfo(name = "pays_id")
    private long paysId;

    public Ville() {
    }

    public Ville(String libelle, long paysId) {
        this.libelle = libelle;
        this.paysId = paysId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getPaysId() {
        return paysId;
    }

    public void setPaysId(long paysId) {
        this.paysId = paysId;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", paysId=" + paysId +
                '}';
    }
}

package com.example.livedataxample.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "localites")
public class Localite implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "libelle")
    private String libelle;
    @ColumnInfo(name = "ville_id")
    private long villeId;

    public Localite(){
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

    public long getVilleId() {
        return villeId;
    }

    public void setVilleId(long villeId) {
        this.villeId = villeId;
    }

    @Override
    public String toString() {
        return "Localite{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", villeId=" + villeId +
                '}';
    }
}

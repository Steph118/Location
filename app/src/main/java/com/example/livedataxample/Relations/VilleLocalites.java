package com.example.livedataxample.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.livedataxample.entites.Localite;
import com.example.livedataxample.entites.Ville;

import java.util.List;

public class VilleLocalites {
    @Embedded
    private Ville ville;

    @Relation(
            parentColumn = "id",
            entityColumn = "ville_id"
    )
    List<Localite> localites;

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public List<Localite> getLocalites() {
        return localites;
    }

    public void setLocalites(List<Localite> localites) {
        this.localites = localites;
    }
}

package com.example.livedataxample.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.livedataxample.entites.Pays;
import com.example.livedataxample.entites.Ville;

import java.util.List;

public class PaysVilles {
    @Embedded private Pays pays;
    @Relation(
            parentColumn = "id",
            entityColumn = "pays_id"
    ) private List<Ville> villes;

    public PaysVilles() {
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}

package com.example.livedataxample.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.livedataxample.entites.Pays;
import com.example.livedataxample.entites.PersonneInfos;

import java.util.List;

public class PaysPersonneInfoss {

    @Embedded
    private Pays pays;

    @Relation(
            parentColumn = "id",
            entityColumn = "nationalite_pays_id"
    ) private List<PersonneInfos> personneInfos;

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<PersonneInfos> getPersonneInfos() {
        return personneInfos;
    }

    public void setPersonneInfos(List<PersonneInfos> personneInfos) {
        this.personneInfos = personneInfos;
    }
}

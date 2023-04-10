package com.example.livedataxample.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.livedataxample.entites.Localite;
import com.example.livedataxample.entites.PersonneInfos;

import java.util.List;

public class LocalitePersonneInfoss {
    @Embedded private Localite localite;

    @Relation(
            parentColumn = "id",
            entityColumn = "localite_id"
    ) private List<PersonneInfos> personneInfos;

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public List<PersonneInfos> getPersonneInfos() {
        return personneInfos;
    }

    public void setPersonneInfos(List<PersonneInfos> personneInfos) {
        this.personneInfos = personneInfos;
    }
}

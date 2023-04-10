package com.example.livedataxample.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.livedataxample.entites.PersonneInfos;
import com.example.livedataxample.entites.User;

import java.util.List;

public class UserPersonneInfos {

    @Embedded
    private PersonneInfos personneInfos;

    @Relation(
            parentColumn = "id",
            entityColumn = "personne_infos_id"
    )private List<User> users;

    public PersonneInfos getPersonneInfos() {
        return personneInfos;
    }

    public void setPersonneInfos(PersonneInfos personneInfos) {
        this.personneInfos = personneInfos;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

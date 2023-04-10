package com.example.livedataxample.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "personne_infos_id")
    private long personneInfos;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPersonneInfos() {
        return personneInfos;
    }

    public void setPersonneInfos(long personneInfos) {
        this.personneInfos = personneInfos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", personneInfos=" + personneInfos +
                '}';
    }
}

package com.example.livedataxample.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.livedataxample.entites.PersonneInfos;

import java.util.List;

@Dao
public interface PersonneInfosDAO {
    @Insert
    void insertOne(PersonneInfos personneInfos);

    @Insert
    void insertOne(PersonneInfos...personneInfos);

    @Query("select * from personnes_infos")
    List<PersonneInfos> getAllPersonnesInfos();

    @Delete
    void deleteOne(PersonneInfos personneInfos);
    @Query("delete from personnes_infos")
    void deleteAll();
    @Update
    void updateOne(PersonneInfos personneInfos);

}

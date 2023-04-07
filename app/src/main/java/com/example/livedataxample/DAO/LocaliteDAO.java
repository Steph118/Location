package com.example.livedataxample.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import com.example.livedataxample.entites.Localite;

import java.util.List;

@Dao
public interface LocaliteDAO {
    @Insert
    void insertOne(Localite localite);

    @Insert
    void insertOne(Localite...localites);

    @Query("select * from localites")
    List<Localite> getAllLocalite();

    @Delete
    void deleteOne(Localite localite);

    @Update
    void updateOne(Localite localite);

}

package com.example.livedataxample.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.livedataxample.entites.Ville;

import java.util.List;

@Dao
public interface VilleDAO {
    @Insert
    void insertOne(Ville ville);

    @Insert
    void insertOne(Ville...villes);

    @Query("select * from villes")
    List<Ville> getAllVilles();

    @Delete
    void deleteOne(Ville ville);

    @Query("delete from villes")
    void deleteAll();

    @Update
    void updateOne(Ville ville);
}

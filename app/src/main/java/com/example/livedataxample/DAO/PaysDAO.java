package com.example.livedataxample.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.livedataxample.entites.Pays;

import java.util.List;

@Dao
public interface PaysDAO {
    @Insert
    void insertOne(Pays pays);
    @Insert
    void insertOne(Pays...pays);
    @Query("select * from pays")
    List<Pays> getAllPays();
    @Delete
    void deleteOne(Pays pays);
    @Update
    void updateOne(Pays pays);
}

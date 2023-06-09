package com.example.livedataxample.DAO;

import androidx.lifecycle.LiveData;
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
    void insertAll(List<Pays> pays);
    @Insert
    void insertOneOrAll(Pays...pays);

    @Query("select * from pays")
    List<Pays> getAllPays();
    @Query("select * from pays order by libelle asc")
    LiveData<List<Pays>> getAllPaysByLiveData();

    @Delete
    void deleteOne(Pays pays);
    @Query("delete from pays")
    void deleteAll();
    @Delete
    void deleteAll(List<Pays> pays);
    @Delete
    void deleteOneAndAll(Pays...pays);

    @Update
    void updateOne(Pays pays);
    @Update
    void updateAll(List<Pays> pays);
    @Update
    void updateOneorAll(Pays...pays);
}

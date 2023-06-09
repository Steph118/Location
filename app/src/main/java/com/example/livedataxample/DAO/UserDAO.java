package com.example.livedataxample.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.livedataxample.entites.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertOne(User user);

    @Insert
    void insertOne(User...users);

    @Query("select * from users")
    List<User> getAllUsers();

    @Delete
    void deleteOne(User user);

    @Query("delete from users")
    void deleteAll();

    @Update
    void updateOne(User user);
}

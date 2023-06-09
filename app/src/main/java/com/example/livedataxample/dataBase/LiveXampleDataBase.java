package com.example.livedataxample.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.livedataxample.DAO.LocaliteDAO;
import com.example.livedataxample.DAO.PaysDAO;
import com.example.livedataxample.DAO.PersonneInfosDAO;
import com.example.livedataxample.DAO.UserDAO;
import com.example.livedataxample.DAO.VilleDAO;
import com.example.livedataxample.entites.Localite;
import com.example.livedataxample.entites.Pays;
import com.example.livedataxample.entites.PersonneInfos;
import com.example.livedataxample.entites.User;
import com.example.livedataxample.entites.Ville;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pays.class, Ville.class, Localite.class,
        PersonneInfos.class, User.class},version = 1, exportSchema = false)
public abstract class LiveXampleDataBase extends RoomDatabase {

    public abstract LocaliteDAO localiteDAO();
    public abstract PaysDAO paysDAO();
    public abstract PersonneInfosDAO personneInfosDAO();
    public abstract UserDAO userDAO();
    public abstract VilleDAO villeDAO ();
    public static volatile LiveXampleDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LiveXampleDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LiveXampleDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    LiveXampleDataBase.class, "room_data_base")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

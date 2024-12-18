package com.example.fishing_pokedex.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fishing_pokedex.entity.Fish;

@Database(entities = {Fish.class}, version = 2, exportSchema = false)
public abstract class FishDatabase extends RoomDatabase {
    public abstract FishDao fishDao();
}

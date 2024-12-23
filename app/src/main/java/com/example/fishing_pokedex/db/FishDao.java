package com.example.fishing_pokedex.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fishing_pokedex.entity.Fish;

import java.util.List;

@Dao
public interface FishDao {
    @Query("SELECT * FROM fish_table")
    LiveData<List<Fish>> getFishList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fish fish);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Fish fish);

    @Delete
    void delete(Fish fish);

    @Query("SELECT * FROM fish_table WHERE name = :name")
    Fish getFishByName(String name);
}

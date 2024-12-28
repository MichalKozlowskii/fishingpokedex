package com.example.fishing_pokedex.di;

import android.content.Context;

import androidx.room.Room;

import com.example.fishing_pokedex.migration.Migration1To2;
import com.example.fishing_pokedex.db.FishDao;
import com.example.fishing_pokedex.db.FishDatabase;
import com.example.fishing_pokedex.migration.Migration2To3;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FishDatabaseModule {

    @Provides
    @Singleton
    public FishDatabase provideFishDatabase(Context context) {
        return Room.databaseBuilder(context, FishDatabase.class, "fish_table")
                .addMigrations(new Migration1To2())
                .addMigrations(new Migration2To3())
                .build();
    }

    @Provides
    public FishDao provideFishDao(FishDatabase database) {
        return database.fishDao();
    }
}

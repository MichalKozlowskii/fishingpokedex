package com.example.fishing_pokedex.di;

import com.example.fishing_pokedex.repository.FishRepository;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface AppEntryPoint {
    FishRepository getFishRepository();
}

package com.example.fishing_pokedex;

import android.app.Application;

import com.example.fishing_pokedex.di.AppEntryPoint;
import com.example.fishing_pokedex.repository.FishRepository;

import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class FishingPokedex extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppEntryPoint entryPoint = EntryPointAccessors.fromApplication(this, AppEntryPoint.class);
        FishRepository repository = entryPoint.getFishRepository();
        repository.initializeFishTable();
    }

}

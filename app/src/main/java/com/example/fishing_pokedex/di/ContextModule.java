package com.example.fishing_pokedex.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)  // Make the module available throughout the app
public class ContextModule {

    @Provides
    public static Context provideContext(Application application) {
        return application.getApplicationContext();  // Provide the application context
    }
}
package com.example.fishing_pokedex.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class ExecutorModule {

    @Provides
    @Singleton
    public Executor provideDiskIOExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}

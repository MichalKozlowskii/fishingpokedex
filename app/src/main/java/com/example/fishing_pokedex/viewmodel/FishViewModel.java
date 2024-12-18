package com.example.fishing_pokedex.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.repository.FishRepository;

import java.util.List;

import javax.inject.Inject;

public class FishViewModel {
    private final FishRepository repository;

    @Inject
    public FishViewModel(FishRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Fish>> getFishList() {
        return repository.getFishList();
    }

    public void insert(Fish fish) {
        repository.insert(fish);
    }

    public void update(Fish fish) {
        repository.update(fish);
    }
}
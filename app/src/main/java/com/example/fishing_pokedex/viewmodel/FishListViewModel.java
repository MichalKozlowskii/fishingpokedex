package com.example.fishing_pokedex.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.repository.FishRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FishListViewModel extends ViewModel {
    private final FishRepository repository;

    @Inject
    public FishListViewModel(FishRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Fish>> getFishList() {
        return repository.getFishList();
    }
}

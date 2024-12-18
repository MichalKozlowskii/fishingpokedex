package com.example.fishing_pokedex.repository;

import androidx.lifecycle.LiveData;

import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.room.FishDao;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class FishRepository {
    private final FishDao dao;
    private final Executor diskIO;

    @Inject
    public FishRepository(FishDao dao, Executor diskIO) {
        this.dao = dao;
        this.diskIO = diskIO;
    }


    public LiveData<List<Fish>> getFishList() {
        return dao.getFishList();
    }

    public void insert(Fish fish) { // diskIO
        diskIO.execute(() -> dao.insert(fish));
    }

    public void update(Fish fish) {
        diskIO.execute(() -> dao.update(fish));
    }

    public void delete(Fish fish) {
        diskIO.execute(() -> dao.delete(fish));
    }

    public void initializeFishTable() {
         List<String> fishList = List.of(
                "Karp bezłuski",
                "Karp królewski",
                "Karp pełnołuski (sezam)",
                "Szczupak",
                "Sandacz",
                "Sum",
                "Leszcz",
                "Płoć",
                "Karaś złocisty",
                "Karaś srebrzysty",
                "Węgorz",
                "Okonie",
                "Amur",
                "Tołpyga",
                "Lin",
                "Boleń",
                "Jelec",
                "Miętus",
                "Troć wędrowna",
                "Lipień",
                "Brzana",
                "Pstrąg potokowy",
                "Pstrąg tęczowy",
                "Wzdręga",
                "Ukleja",
                "Certa",
                "Świnka",
                "Jaź",
                "Jesiotr"
        );

         diskIO.execute(() ->{
            for (String fishName : fishList) {
                Fish exisiting = dao.getFishByName(fishName);

                if (exisiting == null) {
                    Fish newFish = new Fish(fishName, 0.0, false, "");
                    dao.insert(newFish);
                }
            }
        });
    }
}

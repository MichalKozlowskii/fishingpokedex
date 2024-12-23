package com.example.fishing_pokedex.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "fish_table")
public class Fish {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private double weight;
    private boolean isCaught;
    private String photoPath;

    public Fish(String name, double weight, boolean isCaught, String photoPath) {
        this.name = name;
        this.weight = weight;
        this.isCaught = isCaught;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isCaught() {
        return isCaught;
    }

    public void setCaught(boolean caught) {
        isCaught = caught;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return id == fish.id && Double.compare(weight, fish.weight) == 0 && isCaught == fish.isCaught && Objects.equals(name, fish.name) && Objects.equals(photoPath, fish.photoPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, isCaught, photoPath);
    }
}

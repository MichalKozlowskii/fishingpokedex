package com.example.fishing_pokedex.rv;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.fishing_pokedex.entity.Fish;

public class FishComparator extends DiffUtil.ItemCallback<Fish> {
    @Override
    public boolean areItemsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
        return oldItem.equals(newItem);
    }
}

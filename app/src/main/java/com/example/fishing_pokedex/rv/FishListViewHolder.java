package com.example.fishing_pokedex.rv;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fishing_pokedex.databinding.RvItemBinding;
import com.example.fishing_pokedex.entity.Fish;


public class FishListViewHolder extends RecyclerView.ViewHolder {
    private final RvItemBinding binding;

    public FishListViewHolder(RvItemBinding binding, OnItemClickListener onItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(v -> {
            onItemClickListener.onItemClick(getAdapterPosition());
        });
    }

    public void bind(Fish fish) {
        binding.fishNameTextView.setText(fish.getName());

        if (fish.isCaught()) {
            binding.caughtTextView.setText("Złowiona!");
        }
    }
}

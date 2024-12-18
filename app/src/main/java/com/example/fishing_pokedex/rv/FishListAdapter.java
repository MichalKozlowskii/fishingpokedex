package com.example.fishing_pokedex.rv;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.fishing_pokedex.databinding.RvItemBinding;
import com.example.fishing_pokedex.entity.Fish;

import java.util.List;

public class FishListAdapter extends ListAdapter<Fish, FishListViewHolder> {
    private final OnItemClickListener onItemClickListener;

    public FishListAdapter(FishComparator comparator,
                           OnItemClickListener onItemClickListener) {
        super(comparator);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FishListViewHolder(RvItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false),
                onItemClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FishListViewHolder holder, int position) {
        Fish currentItem = getItem(position);
        holder.bind(currentItem);
    }
}

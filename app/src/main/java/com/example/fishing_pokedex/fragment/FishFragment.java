package com.example.fishing_pokedex.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishing_pokedex.databinding.FragmentFishBinding;
import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.viewmodel.FishViewModel;

import java.net.URI;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FishFragment extends Fragment {
    private FragmentFishBinding binding;
    private URI photoUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFishBinding.inflate(inflater);
        FishViewModel viewModel =  new ViewModelProvider(this).get(FishViewModel.class);

        if (getArguments() != null) {
            final int position = getArguments().getInt("position", -1);

            if (position != -1) {
                viewModel.getFishList().observe(getViewLifecycleOwner(), fishList -> {
                    updateUI(fishList.get(position));
                });
            }
        }

        return binding.getRoot();
    }

    private void updateUI(Fish fish) {
        binding.titleTextView.setText(fish.getName());

        if (fish.isCaught() && fish.getPhotoPath() != null) {
            binding.fishPhoto.setImageURI(Uri.parse(fish.getPhotoPath()));
        }
    }
}

package com.example.fishing_pokedex.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fishing_pokedex.R;
import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.viewmodel.FishListViewModel;
import com.example.fishing_pokedex.databinding.FragmentFishListBinding;
import com.example.fishing_pokedex.rv.FishComparator;
import com.example.fishing_pokedex.rv.FishListAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FishListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentFishListBinding binding = FragmentFishListBinding.inflate(inflater);
        FishListViewModel viewModel = new ViewModelProvider(this).get(FishListViewModel.class);

        FishListAdapter adapter = new FishListAdapter(
                new FishComparator(),
                position -> {
                    Bundle args = new Bundle();
                    args.putInt("position", position);

                    Navigation.findNavController(requireView()).navigate(R.id.action_fishListFragment_to_fishFragment, args);
                });

        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireActivity()));

        //viewModel.getFishList().observe(getViewLifecycleOwner(), adapter::submitList);

        viewModel.getFishList().observe(getViewLifecycleOwner(), v -> {
            adapter.submitList(v);
            for (Fish fish : v) {
                Log.d("Fish", "Name: " + fish.getName() + ", Weight: " + fish.getWeight() + ", Caught: " + fish.isCaught() + ", PhotoPath: " + fish.getPhotoPath());
            }
        });

        return binding.getRoot();
    }
}

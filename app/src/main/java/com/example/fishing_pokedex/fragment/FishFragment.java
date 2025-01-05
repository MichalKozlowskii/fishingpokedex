package com.example.fishing_pokedex.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishing_pokedex.databinding.FragmentFishBinding;
import com.example.fishing_pokedex.entity.Fish;
import com.example.fishing_pokedex.viewmodel.FishViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
@AndroidEntryPoint
public class FishFragment extends Fragment {
    private FragmentFishBinding binding;
    private FishViewModel viewModel;
    private Fish fish;
    private String currentPhotoPath;
    private Double currentWeight;

    private static final String[] REQUIRED_PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_MEDIA_IMAGES
    };
    ActivityResultLauncher<String[]> requestPermissionsLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                boolean allGranted = true;
                for (Boolean granted : result.values()) {
                    if (!granted) {
                        allGranted = false;
                        break;
                    }
                }
                if (allGranted) {
                    openCamera();
                } else {
                    binding.permissionsTextView.setVisibility(View.VISIBLE);
                }
            });

    ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && currentPhotoPath != null) {
                        updateFish(currentPhotoPath, currentWeight);
                    }
                }
            }
    );

    private void checkAndRequestPermissions() {
        boolean allGranted = true;
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) {
            openCamera();
        } else {
            requestPermissionsLauncher.launch(REQUIRED_PERMISSIONS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFishBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(FishViewModel.class);

        if (getArguments() != null) {
            final int position = getArguments().getInt("position", -1);

            if (position != -1) {
                viewModel.getFishList().observe(getViewLifecycleOwner(), fishList -> {
                    fish = fishList.get(position);
                    updateUI();
                });
            }

            binding.captureButton.setOnClickListener(view -> {
                String weightInput = binding.weightTextEntry.getText().toString();
                if (weightInput.isEmpty() || weightInput.isBlank()) return;

                currentWeight = Double.valueOf(binding.weightTextEntry.getText().toString());
                if (currentWeight < 0) return;

                Log.d("DEBUG", "Capture button clicked");
                checkAndRequestPermissions();
            });
        }

        return binding.getRoot();
    }

    private void updateUI() {
        binding.titleTextView.setText(fish.getName());

        if (fish.isCaught() && fish.getPhotoPath() != null) {
            binding.weightTextEntry.setText(String.format(Locale.getDefault(),
                    fish.getWeight().toString()));
            binding.fishPhoto.setImageURI(Uri.parse(fish.getPhotoPath()));
        }
    }

    private void updateFish(String photoPath, Double weight) {
        fish.setCaught(true);
        fish.setPhotoPath(photoPath);
        fish.setWeight(weight);
        viewModel.update(fish);
        updateUI();
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photo = null;
            try {
                photo = createImageFile();
                currentPhotoPath = photo.getAbsolutePath();
            } catch (IOException exception) {
                Log.d("PHOTO", "Error creating image file");
                exception.printStackTrace();
            }

            if (photo != null) {
                Uri photoUri = FileProvider.getUriForFile(
                        this.requireContext(),
                        "com.example.fishing_pokedex.fileprovider",
                        photo
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                takePictureLauncher.launch(takePictureIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireContext().getCacheDir();
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        return image;
    }
}

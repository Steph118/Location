package com.example.livedataxample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.livedataxample.databinding.ActivityMainBinding;
import com.example.livedataxample.fragments.LocalisationAgencesFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    public void init(){
        binding.open.setOnClickListener(view -> {
            LocalisationAgencesFragment mapsFragment = LocalisationAgencesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameId,mapsFragment)
                    .addToBackStack("back")
                    .commit();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
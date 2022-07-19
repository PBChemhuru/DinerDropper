package com.example.dinerdropper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.dinerdropper.R;
import com.example.dinerdropper.databinding.ActivityMainBinding;
import com.example.dinerdropper.fragments.homeFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new homeFragment());

        binding.btmNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.homeFragment2:
                    replaceFragment(new homeFragment());
                    break;

            }


            return true;
        });

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fraghost,fragment);
        fragmentTransaction.commit();


    }
}
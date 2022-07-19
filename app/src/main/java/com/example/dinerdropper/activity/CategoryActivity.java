package com.example.dinerdropper.activity;

import static android.nfc.cardemulation.CardEmulation.EXTRA_CATEGORY;

import static com.example.dinerdropper.fragments.homeFragment.EXTRA_POSITION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.dinerdropper.R;
import com.example.dinerdropper.adapter.ViewPagerCategoryAdapter;
import com.example.dinerdropper.databinding.ActivityCategoryBinding;
import com.example.dinerdropper.model.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    List<Categories.Category> categories;

   private ActivityCategoryBinding categoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = ActivityCategoryBinding.inflate(getLayoutInflater());
        categories = (List<Categories.Category>) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        View view = categoryBinding.getRoot();
        setContentView(view);

    initActionBar();
    
    initIntent();


    }

    private void initIntent() {


        int position =getIntent().getIntExtra(EXTRA_POSITION,0);
        ViewPagerCategoryAdapter adapter =new ViewPagerCategoryAdapter (getSupportFragmentManager(),categories);
        ViewPager viewPager = categoryBinding.getRoot().findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = categoryBinding.getRoot().findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();


    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) categoryBinding.getRoot().findViewById(R.id.drawertoolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
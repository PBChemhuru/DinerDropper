package com.example.dinerdropper.activity;

import static com.example.dinerdropper.fragments.homeFragment.EXTRA_DETAIL;
import static com.example.dinerdropper.fragments.homeFragment.EXTRA_SEARCH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Presenter.SearchPresenter;
import com.example.dinerdropper.R;
import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.searchview;
import com.example.dinerdropper.adapter.SearchRecyclerAdapter;
import com.example.dinerdropper.databinding.ActivitySearchBinding;
import com.example.dinerdropper.model.Meals;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements searchview {
    private ActivitySearchBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String search = intent.getStringExtra( EXTRA_SEARCH);
        SearchPresenter presenter = new SearchPresenter(this);
        if(search.length()>1){
            presenter.searchMeal(search);
        }
        else {
            presenter.searchMeals(search);

        }
        EditText searchbar = binding.getRoot().findViewById(R.id.searchmeal);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchbar.getText().clear();
            }
        });
        ImageView searcha = binding.getRoot().findViewById(R.id.search_button);
        searcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SearchActivity.this,SearchActivity.class);
                intent.putExtra((String) EXTRA_SEARCH,searchbar.getText().toString());
                startActivity(intent);

            }
        });



    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.search_recycler);
        SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(this,meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.smealName);
            Intent intent =new Intent(this, DetailActivity.class);
            intent.putExtra((String) EXTRA_DETAIL,mealName.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error ", message);

    }
}
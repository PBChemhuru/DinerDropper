package com.example.dinerdropper.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Presenter.homefragPresenter;
import com.example.dinerdropper.R;
import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.homefragview;
import com.example.dinerdropper.activity.CategoryActivity;
import com.example.dinerdropper.activity.DetailActivity;
import com.example.dinerdropper.activity.SearchActivity;
import com.example.dinerdropper.adapter.RecyclerViewHomeAdapter;
import com.example.dinerdropper.adapter.ViewPagerHeaderAdapter;
import com.example.dinerdropper.databinding.FragmentHomeBinding;
import com.example.dinerdropper.model.Categories;
import com.example.dinerdropper.model.Meals;

import java.io.Serializable;
import java.util.List;

public class homeFragment extends Fragment implements homefragview {
    public static final String EXTRA_POSITION = "position";
    private static final String EXTRA_CATEGORY = "category";
    public static final Object EXTRA_DETAIL = "detail";
    public static final String EXTRA_SEARCH = "search";
    FragmentHomeBinding binding;

    homefragPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new homefragPresenter(this);
        presenter.getMeals();
        presenter.getCatergories();
        EditText searchbar = binding.getRoot().findViewById(R.id.searchmeal);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchbar.getText().clear();
            }
        });
        ImageView search = binding.getRoot().findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SearchActivity.class);
                intent.putExtra((String) EXTRA_SEARCH,searchbar.getText().toString());
                startActivity(intent);

            }
        });




    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPager viewPagerMeal = binding.viewPagerHeader.findViewById(R.id.viewPagerHeader);
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal,getActivity());
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0,150,0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView mealName = binding.getRoot().findViewById(R.id.mealName);
            Intent intent =new Intent(getActivity(), DetailActivity.class);
            intent.putExtra((String) EXTRA_DETAIL,mealName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {

        RecyclerView recyclerViewCategory = binding.recyclerCategory.findViewById(R.id.recyclerCategory);
        RecyclerViewHomeAdapter homeAdapter=new RecyclerViewHomeAdapter(getActivity(),category);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY,(Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);

        });


    }

    @Override
    public void onErrorloading(String message) {
        Utils.showDialogMessage(getActivity(), "Title", message);

    }

    @Override
        public void showloading() {
        binding.getRoot().findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        binding.getRoot().findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
    }


    @Override
    public void hideloading() {
        binding.getRoot().findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        binding.getRoot().findViewById(R.id.shimmerMeal).setVisibility(View.GONE);

    }


}

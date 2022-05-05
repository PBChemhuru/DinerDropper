package com.example.dinerdropper.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dinerdropper.Presenter.CategoryPresenter;
import com.example.dinerdropper.R;
import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.categoryview;
import com.example.dinerdropper.activity.DetailActivity;
import com.example.dinerdropper.adapter.RecyclerViewMealByCategory;
import com.example.dinerdropper.databinding.FragmentCategoriesBinding;
import com.example.dinerdropper.databinding.FragmentHomeBinding;
import com.example.dinerdropper.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryFragment extends Fragment implements categoryview {

    private static final Object EXTRA_DETAIL = "detail";
    private FragmentCategoriesBinding fragmentCategoriesBinding;
    AlertDialog.Builder descDialog;;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCategoriesBinding = FragmentCategoriesBinding.inflate(inflater,container,false);
        View view =fragmentCategoriesBinding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            TextView textCategory =fragmentCategoriesBinding.getRoot().findViewById(R.id.textCategory);
            ImageView imageCategoryBg = fragmentCategoriesBinding.getRoot().findViewById(R.id.imageCategoryBg);
            ImageView imageCategory = fragmentCategoriesBinding.getRoot().findViewById(R.id.imageCategory);
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategory);
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategoryBg);
            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        ProgressBar progressBar = fragmentCategoriesBinding.getRoot().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        ProgressBar progressBar = fragmentCategoriesBinding.getRoot().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerView recyclerView = fragmentCategoriesBinding.getRoot().findViewById(R.id.recyclerView);
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(getActivity(),meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent Dintent =new Intent(getActivity(), DetailActivity.class);
            Dintent.putExtra((String) EXTRA_DETAIL,mealName.getText().toString());
            startActivity(Dintent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }


}
package com.example.dinerdropper.Presenter;

import androidx.annotation.NonNull;

import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.categoryview;
import com.example.dinerdropper.fragments.CategoryFragment;
import com.example.dinerdropper.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
     categoryview view;

    public CategoryPresenter(categoryview view) {
        this.view = view;
    }

    public void getMealByCategory(String category) {

        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.setMeals(response.body().getMeals());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}

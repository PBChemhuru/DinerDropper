package com.example.dinerdropper.Presenter;

import androidx.annotation.NonNull;

import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.detailview;
import com.example.dinerdropper.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private detailview view;
    public DetailPresenter(detailview view){this.view=view;}
    public void getMealById(String mealName){
        view.showLoading();
        Utils.getApi().getMealName(mealName).
                enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {
                        view.hideLoading();
                        if(response.isSuccessful() && response.body() != null){
                            view.setMeal(response.body().getMeals().get(0));
                        }
                        else {
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

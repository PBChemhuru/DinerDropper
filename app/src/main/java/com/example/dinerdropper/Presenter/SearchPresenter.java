package com.example.dinerdropper.Presenter;

import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.searchview;
import com.example.dinerdropper.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    searchview view;

    public SearchPresenter(searchview view){this.view=view;}
    public void searchMeals(String mealName){
        view.showLoading();
        Utils.getApi().getMealNames(mealName).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body()!= null){
                    view.setMeals(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
    public void searchMeal(String mealName){
        view.showLoading();
        Utils.getApi().getMealName(mealName).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body()!= null){
                    view.setMeals(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

}

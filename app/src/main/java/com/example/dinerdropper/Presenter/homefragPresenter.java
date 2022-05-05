package com.example.dinerdropper.Presenter;

import androidx.annotation.NonNull;

import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.homefragview;
import com.example.dinerdropper.model.Categories;
import com.example.dinerdropper.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homefragPresenter {
    homefragview view;

    public homefragPresenter(homefragview view) {
        this.view = view;
    }
    public void getMeals() {
        Call<Meals> mealsCall = Utils.getApi().getmeals();
        mealsCall.enqueue(new Callback<Meals>() {
                              @Override
                              public void onResponse(@NonNull Call<Meals> call, Response<Meals> response) {
                                  view.hideloading();
                                  if(response.isSuccessful()&& response.body() != null){
                                      view.setMeal(response.body().getMeals());
                                  }
                                  else {
                                      view.onErrorloading(response.message());
                                  }
                              }

                              @Override
                              public void onFailure(Call<Meals> call, Throwable t) {
                                  view.onErrorloading(t.getLocalizedMessage());
                              }
                          }
        );
    }

    public void getCatergories(){
        Call<Categories> categoriesCall=  Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                  view.hideloading();
                  if(response.isSuccessful() && response.body() != null){
                      view.setCategory(response.body().getCategories());
                  }
                  else{
                      view.onErrorloading(response.message());
                  }

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
             view.hideloading();
             view.onErrorloading(t.getLocalizedMessage());
            }
        });
    }
}

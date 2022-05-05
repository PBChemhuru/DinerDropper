package com.example.dinerdropper.Views;

import com.example.dinerdropper.model.Meals;

public interface detailview {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);


}

package com.example.dinerdropper.Views;

import com.example.dinerdropper.model.Meals;

import java.util.List;

public interface searchview {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}

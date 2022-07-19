package com.example.dinerdropper.Views;

import com.example.dinerdropper.model.Categories;
import com.example.dinerdropper.model.Meals;

import java.util.List;

public interface homefragview {
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorloading(String message);
    void showloading();
    void hideloading();
}

package com.example.dinerdropper.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private String Meal,Mealnum,Mealprice,Mealthumb;
    public Cart(){}

    public Cart(String meal, String mealnum, String mealprice, String mealthumb) {
        Meal = meal;
        Mealnum = mealnum;
        Mealprice = mealprice;
        Mealthumb = mealthumb;
    }

    public String getMeal() {
        return Meal;
    }

    public void setMeal(String meal) {
        Meal = meal;
    }

    public String getMealnum() {
        return Mealnum;
    }

    public void setMealnum(String mealnum) {
        Mealnum = mealnum;
    }

    public String getMealprice() {
        return Mealprice;
    }

    public void setMealprice(String mealprice) {
        Mealprice = mealprice;
    }

    public String getMealthumb() {
        return Mealthumb;
    }

    public void setMealthumb(String mealthumb) {
        Mealthumb = mealthumb;
    }
}
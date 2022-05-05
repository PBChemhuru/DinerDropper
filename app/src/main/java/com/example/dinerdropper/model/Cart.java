package com.example.dinerdropper.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private String mealName;
    private String mealThumb;
    private int fee;
    private int numberInCart;


    public Cart(String mealName, String mealThumb, int fee, int numberInCart){
        this.mealName = mealName;
        this.mealThumb= mealThumb;
        this.fee= fee;
        this.numberInCart = numberInCart;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}

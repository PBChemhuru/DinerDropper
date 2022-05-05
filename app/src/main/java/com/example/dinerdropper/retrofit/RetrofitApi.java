package com.example.dinerdropper.retrofit;

import com.example.dinerdropper.model.Categories;
import com.example.dinerdropper.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("random.php")
    Call<Meals> getmeals();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals> getMealName(@Query("s") String mealName);


    @GET("search.php")
    Call<Meals> getMealNames(@Query("f") String mealName);
}

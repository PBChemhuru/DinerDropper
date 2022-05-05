package com.example.dinerdropper.activity;

import static com.example.dinerdropper.fragments.homeFragment.EXTRA_DETAIL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Presenter.DetailPresenter;
import com.example.dinerdropper.R;
import com.example.dinerdropper.Utils;
import com.example.dinerdropper.Views.detailview;
import com.example.dinerdropper.databinding.ActivityDetailBinding;
import com.example.dinerdropper.model.Cart;
import com.example.dinerdropper.model.Meals;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import static com.example.dinerdropper.fragments.homeFragment.EXTRA_DETAIL;

public class DetailActivity extends AppCompatActivity implements detailview {
    private ActivityDetailBinding binding;
    Random rand = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setupActionBar();
        Intent intent =getIntent();
        String mealName = intent.getStringExtra((String) EXTRA_DETAIL);

        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealById(mealName);


    }

    private void setupColorActionBarIcon(Drawable favoriteItemColor) {
        Toolbar toolbar= binding.getRoot().findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = binding.getRoot().findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = binding.getRoot().findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });

    }

    private void setupActionBar() {
        Toolbar toolbar= binding.getRoot().findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = binding.getRoot().findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public void setMeal(Meals.Meal meal) {
        Log.d("tag" ,meal.getStrMeal());
        int randomNum = rand.nextInt((40 - 20) + 1) + 20;

        CollapsingToolbarLayout collapsingToolbarLayout = binding.getRoot().findViewById(R.id.collapsing_toolbar);
        ImageView mealThumb = binding.getRoot().findViewById(R.id.mealThumb);
        TextView category = binding.getRoot().findViewById(R.id.category);
        TextView country = binding.getRoot().findViewById(R.id.country);

        TextView ingredients  = binding.getRoot().findViewById(R.id.ingredient);

        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        setupActionBar();

        if(!meal.getStrIngredient1().isEmpty()){
          ingredients.append("\n  \u2022" + meal.getStrIngredient1());
        }
        if(!meal.getStrIngredient2().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient2());
        }
        if(!meal.getStrIngredient3().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient3());
        }
        if(!meal.getStrIngredient4().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient4());
        }
        if(!meal.getStrIngredient5().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient5());
        }
        if(!meal.getStrIngredient6().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient6());
        }
        if(!meal.getStrIngredient7().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient7());
        }
        if(!meal.getStrIngredient8().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient9());
        }
        if(!meal.getStrIngredient10().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient10());
        }
        if(!meal.getStrIngredient11().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient11());
        }
        if(!meal.getStrIngredient12().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient12());
        }
        if(!meal.getStrIngredient13().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient13());
        }
        if(!meal.getStrIngredient14().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient14());
        }
        if(!meal.getStrIngredient15().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient15());
        }
        if(!meal.getStrIngredient16().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient16());
        }
        if(!meal.getStrIngredient17().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient17());
        }
        if(!meal.getStrIngredient18().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient18());
        }
        if(!meal.getStrIngredient19().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient19());
        }
        if(!meal.getStrIngredient20().isEmpty()){
            ingredients.append("\n  \u2022" + meal.getStrIngredient20());
        }
        ImageView plsbtn= binding.getRoot().findViewById(R.id.plusBtn);
        ImageView minusbtn= binding.getRoot().findViewById(R.id.minusBtn);
        TextView numborderTXT = binding.getRoot().findViewById(R.id.numberOrderTxt);
        TextView price = binding.getRoot().findViewById(R.id.priceTxt);
        final int[] numberOrder = {1};
        numborderTXT.setText(String.valueOf(numberOrder[0]));
        price.setText("$" +String.valueOf(rand.nextInt((40 - 20) + 1) + 20));

        plsbtn.setClickable(true);

        plsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder[0]++;
                numborderTXT.setText(String.valueOf(numberOrder[0]));

            }
        });

        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder[0] >1){
                    numberOrder[0]--;
                    numborderTXT.setText(String.valueOf(numberOrder[0]));
                }

            }
        });



        Button addtocart = binding.getRoot().findViewById(R.id.addToCartBtn);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(DetailActivity.this, numborderTXT.getText() +" "+ meal.getStrMeal() + " added to cart",Toast.LENGTH_SHORT);
                toast.show();
                String order=meal.getStrMeal()+" "+meal.getStrMealThumb()+" "+numborderTXT.getText()+" "+price.getText();
                Log.d("TAG",order);


            }
        });








    }
    @Override
    public void showLoading() {
        ProgressBar progressBar = binding.getRoot().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        ProgressBar progressBar = binding.getRoot().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error ", message);

    }
            @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_detail, menu);
            MenuItem favoriteItem = menu.findItem(R.id.favorite);
            Drawable favoriteItemColor = favoriteItem.getIcon();
            setupColorActionBarIcon(favoriteItemColor);
            return true;
        }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.example.dinerdropper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.example.dinerdropper.databinding.ActivityHomedrawerBinding;
import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class HomedrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomedrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomedrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomedrawer.drawertoolbar);
        binding.appBarHomedrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomedrawerActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_cart,R.id.nav_settings,R.id.nav_orders,R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homedrawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_logout)
                {
                    Paper.book().destroy();
                    Intent intent = new Intent(HomedrawerActivity.this,LancherActivity.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.nav_settings)
                {
                    Intent intent = new Intent(HomedrawerActivity.this,SettingsActivity.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.nav_cart)
                {
                    Intent intent = new Intent(HomedrawerActivity.this,CartActivity.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.nav_orders)
                {
                    Intent intent = new Intent(HomedrawerActivity.this,OrderActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        TextView usernameText = headerView.findViewById(R.id.user_profile_name);
        usernameText.setText(Prevalent.CurrentOnlineUser.getName());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homedrawer, menu);
        return true;
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homedrawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
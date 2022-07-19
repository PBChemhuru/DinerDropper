package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.example.dinerdropper.databinding.ActivityLancherBinding;
import com.example.dinerdropper.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LancherActivity extends AppCompatActivity {
    private ActivityLancherBinding binding;
    ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLancherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button joinnowbtn = binding.getRoot().findViewById(R.id.join_nowbtn);
        Button loginbtn = binding.getRoot().findViewById(R.id.loginbtn);
        loadingbar = new ProgressDialog(this);
        Paper.init(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LancherActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        joinnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LancherActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        String UserPhoneNumber =Paper.book().read(Prevalent.UserPhoneNumber);
        String UserPassword =Paper.book().read(Prevalent.UserPassword);

        if(UserPassword != "" && UserPhoneNumber!="")
        {
            if(!TextUtils.isEmpty(UserPhoneNumber) && !TextUtils.isEmpty(UserPassword))
            {
                AllowAccess(UserPhoneNumber,UserPassword);

                loadingbar.setTitle("Already Logged In.");
                loadingbar.setMessage("Please wait.");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();
            }
        }


    }

    private void AllowAccess(final String phone, String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("Users").child(phone).exists())
                {
                    Users usersData = snapshot.child("Users").child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            Toast.makeText(LancherActivity.this,"You are logged in Already.",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent intent = new Intent(LancherActivity.this,HomedrawerActivity.class);
                            Prevalent.CurrentOnlineUser =usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            loadingbar.dismiss();
                            Toast.makeText(LancherActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LancherActivity.this,"Account with this"+phone+ "number do not exists.",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


}
package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class SettingsActivity extends AppCompatActivity {

    EditText fullNameEditText,addressEditText,passwordEdittext;
    TextView CloseTextBtn,saveTextButton;
    Button security;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fullNameEditText = findViewById(R.id.setting_fullname);
        addressEditText = findViewById(R.id.setting_address);
        CloseTextBtn = findViewById(R.id.setting_closebtn);
        saveTextButton = findViewById(R.id.setting_updatebtn);
        passwordEdittext = findViewById(R.id.setting_password);
        security = findViewById(R.id.securityquesiton);
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,ResetPasswordActivity.class);
                intent.putExtra("check","settings");
                startActivity(intent);
            }
        });

        userInfoDisplay(fullNameEditText,addressEditText,passwordEdittext);

        CloseTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                {
                    updateOnlyUserInfo();
                }
            }
        });

    }

    private void updateOnlyUserInfo()
    {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        if(TextUtils.isEmpty(fullNameEditText.getText().toString()))
        {
            if(TextUtils.isEmpty(addressEditText.getText().toString()))
            {
                if(TextUtils.isEmpty(passwordEdittext.getText().toString()))
                {
                    Toast.makeText(this,"Fill in all info to change info",Toast.LENGTH_SHORT);
                }
                else
                {
                    userMap.put("password", passwordEdittext.getText().toString());
                    ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
                }
            }
            else
            {
                if(TextUtils.isEmpty(passwordEdittext.getText().toString()))
                {
                    userMap.put("address", addressEditText.getText().toString());
                    ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
                }
                else
                {
                    userMap.put("address", addressEditText.getText().toString());
                    userMap.put("password", passwordEdittext.getText().toString());
                    ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
                }
            }
            Toast.makeText(this,"Fill in all info to change info",Toast.LENGTH_SHORT);
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            if(TextUtils.isEmpty(passwordEdittext.getText().toString()))
            {
                userMap.put("name", fullNameEditText.getText().toString());
                ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
            }
            else
            {
                userMap.put("name", fullNameEditText.getText().toString());
                userMap.put("password", passwordEdittext.getText().toString());
                ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
            }
        }
        else if(TextUtils.isEmpty(passwordEdittext.getText().toString()))
        {
            userMap.put("name", fullNameEditText.getText().toString());
            userMap.put("address", addressEditText.getText().toString());
            ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);
        }
        else
        {
            userMap.put("name", fullNameEditText.getText().toString());
            userMap.put("address", addressEditText.getText().toString());
            userMap.put("password", passwordEdittext.getText().toString());
            ref.child(Prevalent.CurrentOnlineUser.getPhone()).updateChildren(userMap);

            startActivity(new Intent(this, LancherActivity.class));
            Paper.book().destroy();
            Toast.makeText(this,"Profile Info updated",Toast.LENGTH_SHORT);
            finish();
        }


    }


    private void userInfoDisplay(EditText fullNameEditText, EditText addressEditText, EditText passwordEdittext)
    {
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.CurrentOnlineUser.getPhone());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.child("address").exists()) {
                        String address = snapshot.child("address").getValue().toString();
                        String name = snapshot.child("name").getValue().toString();
                        String password = snapshot.child("password").getValue().toString();

                        fullNameEditText.setText(name);
                        passwordEdittext.setText(password);
                        addressEditText.setText(address);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(SettingsActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });
    }
}
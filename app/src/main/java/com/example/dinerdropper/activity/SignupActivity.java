package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dinerdropper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    Button createAccountButton;
    EditText InputName,InputPhoneNumber,InputPassword,CInputPassword;
    ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createAccountButton= findViewById(R.id.regi_nowbtn);
        InputName=findViewById(R.id.regi_username);
        InputPhoneNumber= findViewById(R.id.regi_phonenumber);
        InputPassword= findViewById(R.id.regi_password);
        CInputPassword = findViewById(R.id.regi_confirmpassword);

        loadingbar = new ProgressDialog(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String cpassword = CInputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please write your name.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please write your phone number.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write your password.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cpassword))
        {
            Toast.makeText(this,"Please confirm your password.",Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(cpassword))
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait,while we are checking credentials.");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            ValidatephoneNumber(name,phone,password);
        }
        else
        {
            Toast.makeText(this,"Passwords do not match.",Toast.LENGTH_SHORT).show();
        }
    }

    private void ValidatephoneNumber(String name, String phone, String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignupActivity.this,"Account has being created.",Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SignupActivity.this,"Network Error:Please try again.",Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();


                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignupActivity.this,"This "+ phone +" already exists.",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(SignupActivity.this,"Please try again using another phone number.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,LancherActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPasswordActivity extends AppCompatActivity {

    String check ="";
    TextView pagetTitle,titleQuestions;
    EditText phonenumber,question1,question2;
    Button verifytbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        check = getIntent().getStringExtra("check");
        pagetTitle = findViewById(R.id.titleresetpassword);
        titleQuestions = findViewById(R.id.resetText);
        phonenumber = findViewById(R.id.resetPhone);
        question1 = findViewById(R.id.resetq1);
        question2 = findViewById(R.id.resetq2);
        verifytbtn = findViewById(R.id.verifybtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        phonenumber.setVisibility(View.GONE);
        if(check.equals("settings"))
        {
            pagetTitle.setText("Set Questions");
            titleQuestions.setText("Please set answer for the following Security questions.");
            verifytbtn.setText("Set Answers");
            displayAnswers();
            verifytbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    setAnswers();

                }
            });

        }
        else if(check.equals("login"))
        {
            phonenumber.setVisibility(View.VISIBLE);
            verifytbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    verifyUser();
                }
            });

        };
    }

    private void verifyUser()
    {
        String phone = phonenumber.getText().toString();
        String answer1 = question1.getText().toString().toLowerCase();
        String answer2 = question2.getText().toString().toLowerCase();

        if(!phone.equals("") && !answer1.equals("") && !answer2.equals("")){

        DatabaseReference ref = FirebaseDatabase.getInstance("https://dinnerdropper-ce772-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(phone);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    String usernumber = snapshot.child("phone").getValue().toString();
                    if(snapshot.hasChild("Security Questions"))
                    {
                        String asn1=snapshot.child("Security Questions").child("answer1").getValue().toString();
                        String asn2=snapshot.child("Security Questions").child("answer2").getValue().toString();

                        if(!asn1.equals(answer1))
                        {
                            Toast.makeText(ResetPasswordActivity.this,"Your first answer is incorrect.",Toast.LENGTH_SHORT).show();

                        }
                        else if(!asn2.equals(answer2))
                        {
                            Toast.makeText(ResetPasswordActivity.this,"Your second answer is incorrect.",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
                            builder.setTitle("New Password");
                            final EditText newpassword = new EditText(ResetPasswordActivity.this);
                            newpassword.setHint("Write new password here..");
                            builder.setView(newpassword);
                            builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    if(!newpassword.getText().toString().equals(""))
                                    {
                                        ref.child("password").setValue(newpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(ResetPasswordActivity.this,"Password changed successfully.",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                                                    startActivity(intent);

                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ResetPasswordActivity.this,"You have not set the security questions.",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(ResetPasswordActivity.this,"This phone number does not exist.",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }
        else
        {
            Toast.makeText(ResetPasswordActivity.this,"Please answer the security questions.",Toast.LENGTH_SHORT).show();

        }


    }

    private void setAnswers()
    {
        String answer1 = question1.getText().toString().toLowerCase();
        String answer2 = question2.getText().toString().toLowerCase();

        if(question1.equals("") && question2.equals(""))
        {
            Toast.makeText(ResetPasswordActivity.this,"Please answer both questions",Toast.LENGTH_SHORT).show();
        }
        else
        {
            DatabaseReference ref = FirebaseDatabase.getInstance("https://dinnerdropper-ce772-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(Prevalent.CurrentOnlineUser.getPhone());
            HashMap<String, Object> questiondataMap = new HashMap<>();
            questiondataMap.put("answer1", answer1);
            questiondataMap.put("answer2", answer2);
            ref.child("Security Questions").updateChildren(questiondataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ResetPasswordActivity.this,"Security questions set.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this,HomedrawerActivity.class);
                        startActivity(intent);
                    }
                }
            });


        }
    }

    private void displayAnswers()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance("https://dinnerdropper-ce772-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(Prevalent.CurrentOnlineUser.getPhone());
        ref.child("Security Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String asn1=snapshot.child("answer1").getValue().toString();
                    String asn2=snapshot.child("answer2").getValue().toString();

                    question1.setText(asn1);
                    question2.setText(asn2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
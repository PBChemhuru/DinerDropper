package com.example.dinerdropper.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmOrderActivity extends AppCompatActivity {
    public EditText nameEditText,phoneEditText,addressEditText;
    public Button confirmOrderBtn;
    String totalAmount = "";
    public CheckBox delivery,online;
    ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        totalAmount =getIntent().getStringExtra("Total Price");
        Toast.makeText(ConfirmOrderActivity.this,"Total Price: $"+totalAmount,Toast.LENGTH_SHORT).show();
        nameEditText = findViewById(R.id.dropoffname);
        phoneEditText = findViewById(R.id.dropoffphonenumber);
        addressEditText = findViewById(R.id.dropoffadress);
        delivery = findViewById(R.id.OnD_checkbox);
        online = findViewById(R.id.Online_checkbox);
        loadingbar = new ProgressDialog(this);
        displayinfo();
        confirmOrderBtn = findViewById(R.id.confirmBtn);

        confirmOrderBtn.setOnClickListener(v -> {
            if(TextUtils.isEmpty(nameEditText.getText().toString()))
            {
                Toast.makeText(ConfirmOrderActivity.this,"Please fill in all details before confirming.",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(phoneEditText.getText().toString()))
            {
                Toast.makeText(ConfirmOrderActivity.this,"Please fill in all details before confirming.",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(addressEditText.getText().toString()))
            {
                Toast.makeText(ConfirmOrderActivity.this,"Please fill in all details before confirming.",Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(delivery.isChecked()) {
                    confirmOrder();
                }
                else if(online.isChecked())
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ConfirmOrderActivity.this);

                    alert.setTitle("Payment");
                    alert.setMessage("Enter Card Details");

                    final EditText input = new EditText(ConfirmOrderActivity.this);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            loadingbar.setTitle("Processing Payment");
                            loadingbar.setMessage("Please wait,while we are checking credentials.");
                            loadingbar.setCanceledOnTouchOutside(false);
                            loadingbar.show();
                            Runnable progressRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    loadingbar.cancel();
                                }
                            };

                            Handler pdCanceller = new Handler();
                            pdCanceller.postDelayed(progressRunnable, 10000);

                            loadingbar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    loadingbar.dismiss();
                                }
                            });
                            confirmOrder();

                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(ConfirmOrderActivity.this,ConfirmOrderActivity.class);
                            startActivity(intent);
                        }
                    });

                    alert.show();
                }
                else
                {
                    Toast.makeText(ConfirmOrderActivity.this, "Select Payment Method", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void displayinfo()
    {
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.CurrentOnlineUser.getPhone());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.child("address").exists()) {
                        String address = snapshot.child("address").getValue().toString();
                        String name = snapshot.child("name").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();

                        nameEditText.setText(name);
                        phoneEditText.setText(phone);
                        addressEditText.setText(address);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(ConfirmOrderActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void confirmOrder()
    {
        String saveCurrentDate,saveCurrentTime;
        Calendar callForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM, yyyy");
        saveCurrentDate=currentDate.format(callForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime=currentTime.format(callForDate.getTime());
        DatabaseReference orderRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Order").child(Prevalent.CurrentOnlineUser.getPhone());
        final HashMap<String,Object> orderMap = new HashMap<>();

        orderMap.put("Name",nameEditText.getText().toString());
        orderMap.put("PhoneNumber",phoneEditText.getText().toString());
        orderMap.put("Address",addressEditText.getText().toString());
        orderMap.put("Date",saveCurrentDate);
        orderMap.put("Time",saveCurrentTime);
        orderMap.put("Totalamount","$"+totalAmount);

        orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                   FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Cart List").child("User View").child(Prevalent.CurrentOnlineUser.getPhone()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task)
                       {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(ConfirmOrderActivity.this, "Order has been placed", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(ConfirmOrderActivity.this,HomedrawerActivity.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               startActivity(intent);
                               finish();
                           }
                       }
                   });

                }
        }

    });

    }
}
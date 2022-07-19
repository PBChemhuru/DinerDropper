package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.example.dinerdropper.adapter.CartRecyclerAdapter;
import com.example.dinerdropper.adapter.CartViewHolder;
import com.example.dinerdropper.model.Cart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartrecyclerView;
    CartRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button checkout;
    TextView totalamount;
    ImageView additem,subitem;
    int cartTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkout = findViewById(R.id.Checkout);
        totalamount = findViewById(R.id.totalTxt);
//        final DatabaseReference cartListRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Cart List");
        cartrecyclerView= findViewById(R.id.orderlist);
        cartrecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View").child(Prevalent.CurrentOnlineUser.getPhone()).child("Products"),Cart.class).build();
//        adapter = new CartRecyclerAdapter(options);
//        cartrecyclerView.setAdapter(adapter);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (cartrecyclerView.getChildCount() == 0)
                {
                    Toast.makeText(CartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CartActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("Total Price", String.valueOf(cartTotalPrice));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View").child(Prevalent.CurrentOnlineUser.getPhone()).child("Products"),Cart.class).build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter2 = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model)
            {
                holder.txtMealName.setText(model.getMeal());
                holder.txtMealQuantity.setText("Quantity: " + model.getMealnum());
                holder.txtMealprice.setText("$"+ model.getMealprice());
                String mealTotalPrice = String.valueOf(Integer.parseInt(model.getMealnum())*Integer.parseInt(model.getMealprice()));
                holder.txtMealtotalprice.setText(mealTotalPrice);
                cartTotalPrice = cartTotalPrice + (Integer.parseInt(model.getMealnum())*Integer.parseInt(model.getMealprice()));
                totalamount.setText("Total: $"+ cartTotalPrice);
                String strMealthumb= model.getMealthumb();
                Picasso.get().load(strMealthumb).into(holder.cartmealimage);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Options: ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i)
                            {
                                if(i==0)
                                {
                                    Intent intent = new Intent(CartActivity.this, DetailActivity.class);
                                    intent.putExtra("detail",model.getMeal());
                                    startActivity(intent);
                                }
                                if(i==1)
                                {
                                    cartListRef.child("User View").child(Prevalent.CurrentOnlineUser.getPhone()).child("Products").child(model.getMeal()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(CartActivity.this,"Item removed",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(CartActivity.this, HomedrawerActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }

                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_cart,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        cartrecyclerView.setAdapter(adapter2);
        adapter2.startListening();
        }

}
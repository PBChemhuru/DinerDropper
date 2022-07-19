package com.example.dinerdropper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dinerdropper.Prevalent;
import com.example.dinerdropper.R;
import com.example.dinerdropper.model.Orders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rey.material.widget.Button;

public class OrderActivity extends AppCompatActivity {
    public RecyclerView ordersList;
    public DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ordersRef = FirebaseDatabase.getInstance("https://dinnerdropper-fb12f-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Order").child(Prevalent.CurrentOnlineUser.getPhone());
        ordersList = findViewById(R.id.orderlist);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Orders> option = new FirebaseRecyclerOptions.Builder<Orders>().setQuery(ordersRef,Orders.class).build();

        FirebaseRecyclerAdapter<Orders,OrdersViewHolder> adapter = new FirebaseRecyclerAdapter<Orders, OrdersViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull OrdersViewHolder holder, int position, @NonNull Orders model)
            {
                holder.username.setText("Name: "+model.getName());
                holder.Phonenumber.setText("Phone Number: "+model.getPhoneNumber());
                holder.address.setText("Address: "+model.getAddress());
                holder.datetime.setText("Ordered at: "+model.getDate()+" "+ model.getTime());
                holder.orderprice.setText("Order Price: $"+model.getTotalamount());

            }

            @NonNull
            @Override
            public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitem_recycler,parent,false);
                return new OrdersViewHolder(view);
            }
        };
        ordersList.setAdapter(adapter);
        adapter.startListening();
    }
    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView username,Phonenumber,address,datetime,orderprice;
        public Button showOrder;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.orderUsername);
            Phonenumber = itemView.findViewById(R.id.orderPhone_number);
            address = itemView.findViewById(R.id.orderAddress);
            datetime = itemView.findViewById(R.id.orderDateTime);
            orderprice = itemView.findViewById(R.id.order_Price);
        }
    }
}
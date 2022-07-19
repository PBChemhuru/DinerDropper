package com.example.dinerdropper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinerdropper.R;
import com.example.dinerdropper.model.Cart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class CartRecyclerAdapter extends FirebaseRecyclerAdapter<Cart,CartRecyclerAdapter.cartrecyclerViewholder> {

    public CartRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Cart> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CartRecyclerAdapter.cartrecyclerViewholder holder, int position, @NonNull Cart model)
    {
       holder.txtMealName.setText(model.getMeal());
       holder.txtMealQuantity.setText("Quantity: " + model.getMealnum());
       holder.txtMealprice.setText("$"+ model.getMealprice());
       holder.txtMealtotalprice.setText(String.valueOf(Integer.parseInt(model.getMealnum())*Integer.parseInt(model.getMealprice())));
       String strMealthumb= model.getMealthumb();
       Picasso.get().load(strMealthumb).into(holder.cartmealimage);

    }

    @NonNull
    @Override
    public CartRecyclerAdapter.cartrecyclerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_cart,parent,false);
        return new CartRecyclerAdapter.cartrecyclerViewholder(view);
    }

    public class cartrecyclerViewholder extends RecyclerView.ViewHolder {
        public TextView txtMealName, txtMealprice,txtMealQuantity,txtMealtotalprice;
        public ImageView cartmealimage;

        public cartrecyclerViewholder(@NonNull View itemView) {
            super(itemView);
            txtMealName =itemView.findViewById(R.id.cartmealname);
            txtMealtotalprice = itemView.findViewById(R.id.mealtotalprice);
            txtMealprice = itemView.findViewById(R.id.cartmealprice);
            txtMealQuantity = itemView.findViewById(R.id.cartmealQuanitity);
            cartmealimage = itemView.findViewById(R.id.picCart);

        }
    }
}

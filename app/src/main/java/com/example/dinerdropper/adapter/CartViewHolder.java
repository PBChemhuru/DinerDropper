package com.example.dinerdropper.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinerdropper.ItemClickListner;
import com.example.dinerdropper.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtMealName, txtMealprice,txtMealQuantity,txtMealtotalprice;
    public ImageView cartmealimage;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMealName =itemView.findViewById(R.id.cartmealname);
        txtMealtotalprice = itemView.findViewById(R.id.mealtotalprice);
        txtMealprice = itemView.findViewById(R.id.cartmealprice);
        txtMealQuantity = itemView.findViewById(R.id.cartmealQuanitity);
        cartmealimage = itemView.findViewById(R.id.picCart);
    }

    @Override
    public void onClick(View v)
    {
        itemClickListner.onClick(v,getAbsoluteAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
}

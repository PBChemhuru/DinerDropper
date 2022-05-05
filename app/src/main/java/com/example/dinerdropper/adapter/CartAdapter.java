package com.example.dinerdropper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinerdropper.R;
import com.example.dinerdropper.model.Cart;
import com.example.dinerdropper.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Cartviewholder> {
    private List<Cart> cartList;
    private Context context;
    private static RecyclerViewHomeAdapter.ClickListener cclickListener;


    public CartAdapter(Context context, List<Cart> cartList){
        this.context =context;
        this.cartList = cartList;

    }
    @NonNull
    @Override
    public CartAdapter.Cartviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_cart,viewGroup,false);
        return new Cartviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Cartviewholder holder, int i) {
        String strItemName = cartList.get(i).getMealName();
        Cartviewholder.mealName.setText(strItemName);
        String strItemThumb = cartList.get(i).getMealThumb();
        Picasso.get().load(strItemThumb).into(Cartviewholder.mealThumb);
        Cartviewholder.feeEachItem.setText(String.valueOf(cartList.get(i).getFee()));
        Cartviewholder.num.setText(String.valueOf(cartList.get(i).getNumberInCart()));
        Cartviewholder.totalEachItem.setText(String.valueOf(Math.round((cartList.get(i).getNumberInCart()) * (cartList.get(i).getFee() * 100)) / 100));

        Cartviewholder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Cartviewholder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {

        return cartList.size();
    }

    public static final class Cartviewholder extends RecyclerView.ViewHolder{
        static ImageView mealThumb;
        static ImageView plusItem;
        static ImageView minusItem;
        static TextView mealName;
        static TextView feeEachItem;
        static TextView totalEachItem;
        static TextView num;

        public Cartviewholder(@NonNull View itemView) {
            super(itemView);
            mealThumb= itemView.findViewById(R.id.mealThumb);
            mealName =itemView.findViewById(R.id.mealName);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }

    }
}

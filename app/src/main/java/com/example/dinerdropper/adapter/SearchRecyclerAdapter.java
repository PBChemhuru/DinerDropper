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
import com.example.dinerdropper.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchHolder> {
    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener searchclickListener;

    public SearchRecyclerAdapter(Context context,List<Meals.Meal> meals){
        this.context= context;
        this.meals= meals;
    }
    @NonNull
    @Override
    public SearchRecyclerAdapter.SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_items,viewGroup,false);
        return new SearchHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.SearchHolder holder, int i) {
        String strMealthumb= meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealthumb).into(holder.smealThumb);

        String strMealName = meals.get(i).getStrMeal();
        holder.smealName.setText(strMealName);


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView smealThumb= itemView.findViewById(R.id.smealThumb);
        TextView smealName = itemView.findViewById(R.id.smealName);

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
        }


        @Override
        public void onClick(View v) {
            searchclickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        SearchRecyclerAdapter.searchclickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
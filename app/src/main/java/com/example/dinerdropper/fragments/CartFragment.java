package com.example.dinerdropper.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dinerdropper.Presenter.homefragPresenter;
import com.example.dinerdropper.R;
import com.example.dinerdropper.activity.DetailActivity;
import com.example.dinerdropper.adapter.CartAdapter;
import com.example.dinerdropper.adapter.RecyclerViewHomeAdapter;
import com.example.dinerdropper.adapter.ViewPagerHeaderAdapter;
import com.example.dinerdropper.databinding.FragmentCartBinding;
import com.example.dinerdropper.databinding.FragmentHomeBinding;
import com.example.dinerdropper.model.Cart;
import com.example.dinerdropper.model.Meals;

import java.util.List;


public class CartFragment extends Fragment {
    private FragmentCartBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//      setCartRecycler();



    }
    public void setCartRecycler(List<Cart> cartList) {
        RecyclerView Cartadapter = binding.cartView.findViewById(R.id.cartView);
        CartAdapter cartAdapter=new CartAdapter(getActivity(),cartList);
        Cartadapter.setAdapter(cartAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        Cartadapter.setLayoutManager(layoutManager);
        Cartadapter.setNestedScrollingEnabled(true);
        };
    }

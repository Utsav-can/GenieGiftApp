package com.app.geniegiftapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView cartList = view.findViewById(R.id.cart_list);
        cartList.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList.setAdapter(new GiftAdapter(GiftData.getInstance().getCart(), true)); // Fixed: added true
        return view;
    }
}
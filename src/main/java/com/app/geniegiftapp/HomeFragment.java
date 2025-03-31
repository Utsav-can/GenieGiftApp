package com.app.geniegiftapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements GiftData.OnGiftsLoadedListener {
    private GiftAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView giftList = view.findViewById(R.id.gift_list);
        giftList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GiftAdapter(GiftData.getInstance().getGifts(), false);
        giftList.setAdapter(adapter);
        GiftData.getInstance().setOnGiftsLoadedListener(this);
        return view;
    }

    @Override
    public void onGiftsLoaded() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
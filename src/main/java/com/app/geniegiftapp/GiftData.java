package com.app.geniegiftapp;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftData {
    private static GiftData instance;
    private List<Gift> gifts = new ArrayList<>();
    private List<Gift> cart = new ArrayList<>();
    private OnGiftsLoadedListener listener;

    private GiftData() {
        fetchGifts();
    }

    public static GiftData getInstance() {
        if (instance == null) {
            instance = new GiftData();
        }
        return instance;
    }

    public void setOnGiftsLoadedListener(OnGiftsLoadedListener listener) {
        this.listener = listener;
        if (!gifts.isEmpty() && listener != null) {
            listener.onGiftsLoaded();
        }
    }

    private void fetchGifts() {
        GenieGiftApp.getGiftApi().getGifts().enqueue(new Callback<List<Gift>>() {
            @Override
            public void onResponse(Call<List<Gift>> call, Response<List<Gift>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    gifts.clear();
                    int count = 0;
                    for (Gift gift : response.body()) {
                        if (count++ >= 10) break; // Limit to 10
                        gifts.add(new Gift(gift.getName(), 19.99 + gifts.size(), gift.getImageUrl()));
                    }
                    if (listener != null) {
                        listener.onGiftsLoaded();
                    }
                } else if (listener != null) {
                    listener.onError("Failed to load gifts: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Gift>> call, Throwable t) {
                if (listener != null) {
                    listener.onError("Network error: " + t.getMessage());
                }
            }
        });
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public List<Gift> getCart() {
        return cart;
    }

    public void addToCart(Gift gift) {
        cart.add(gift);
    }

    public void removeFromCart(Gift gift) {
        cart.remove(gift);
    }

    public interface OnGiftsLoadedListener {
        void onGiftsLoaded();
        void onError(String message);
    }
}
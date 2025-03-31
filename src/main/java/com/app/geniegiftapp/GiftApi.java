package com.app.geniegiftapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GiftApi {
    @GET("photos")
    Call<List<Gift>> getGifts();
}
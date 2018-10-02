package com.sagar.retrofit2.http;

import com.sagar.retrofit2.http.apimodel.Places;
import retrofit2.Call;
import retrofit2.http.GET;


public interface PlacesApi {

    @GET("&type=shopping_mall")
    Call<Places> getTopPlaces();
}

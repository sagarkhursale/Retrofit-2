package com.sagar.retrofit2.http;

import com.sagar.retrofit2.http.apimodel.Places;
import retrofit2.Call;
import retrofit2.http.GET;


public interface PlacesApi {

    @GET("json?location=18.498072,73.8328396&radius=2000&key=AIzaSyCRQIpvfeQHo8eN-hxRQOJe_I7r2TU-85Q&type=shopping_mall")
    Call<Places> getTopPlaces();
}

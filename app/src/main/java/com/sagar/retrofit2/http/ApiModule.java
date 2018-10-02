package com.sagar.retrofit2.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    private final String BASE_URL= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
            "location=18.498072,73.8328396&radius=2000&key=AIzaSyCRQIpvfeQHo8eN-hxRQOJe_I7r2TU-85Q";


    @Provides
    public OkHttpClient providerClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    public PlacesApi provideApiService() {
        return provideRetrofit(BASE_URL, providerClient()).create(PlacesApi.class);
    }


    // END
}

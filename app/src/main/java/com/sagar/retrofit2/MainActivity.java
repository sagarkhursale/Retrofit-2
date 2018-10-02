package com.sagar.retrofit2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sagar.retrofit2.http.PlacesApi;
import com.sagar.retrofit2.http.apimodel.Places;
import com.sagar.retrofit2.http.apimodel.Result;
import com.sagar.retrofit2.root.App;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Inject
    PlacesApi placesApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);


        Call<Places> call = placesApi.getTopPlaces();

        call.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(@NonNull Call<Places> call, @NonNull Response<Places> response) {
                List<Result> placeList = Objects.requireNonNull(response.body()).getResults();

                for (Result result : placeList) {
                    Log.i(TAG, result.getName());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Places> call, @NonNull Throwable t) {
                Log.e(TAG, "OnFailure : " + t.getMessage());
            }
        });


        // end
    }


    // END
}

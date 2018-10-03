package com.sagar.retrofit2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sagar.retrofit2.http.PlacesApi;
import com.sagar.retrofit2.http.apimodel.Places;
import com.sagar.retrofit2.http.apimodel.Result;
import com.sagar.retrofit2.root.App;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private static String mResult="Reactive Api Call >";

    private TextView textView_Result;

    @Inject
    PlacesApi placesApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        textView_Result=findViewById(R.id.txt_result);


        // retrofit api call
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


        // reactive api call
        placesApi.getTopPlacesObservables()
                .flatMap(new Function<Places, ObservableSource<Result>>() {
                    @Override
                    public ObservableSource<Result> apply(Places places) throws Exception {
                        return Observable.fromIterable(places.getResults());
                    }
                })
                .flatMap(new Function<Result, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Result result) throws Exception {
                        return Observable.just(result.getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String selection) {
                        mResult=mResult+"\n"+selection;
                        //Log.i(TAG, "Rx : " + selection);
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView_Result.setText(e.getMessage());
                        Log.i(TAG, "Rx Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        textView_Result.setText(mResult);
                        Log.i(TAG, "Rx Complete");
                    }
                });


        // end
    }


    // END
}

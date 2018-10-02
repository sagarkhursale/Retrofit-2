package com.sagar.retrofit2.root;

import android.app.Application;
import com.sagar.retrofit2.http.ApiModule;



public class App extends Application {
    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .build();
    }


    public ApplicationComponent getComponent() {
        return component;
    }


    // END
}

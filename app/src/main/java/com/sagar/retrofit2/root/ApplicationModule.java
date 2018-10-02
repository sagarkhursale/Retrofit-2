package com.sagar.retrofit2.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class ApplicationModule {
    private Application application;


    ApplicationModule(Application application) {
        this.application = application;
    }


    @Provides
    @Singleton
    public Context providerContext() {
        return application;
    }


    // END
}

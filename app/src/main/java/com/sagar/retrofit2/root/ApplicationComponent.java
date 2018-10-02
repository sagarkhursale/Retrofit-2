package com.sagar.retrofit2.root;

import com.sagar.retrofit2.MainActivity;
import com.sagar.retrofit2.http.ApiModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);

}

package com.example.dagger2advanced;


import com.example.dagger2advanced.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    /** BaseApplication extended from DaggerApplication,
     *  because it allows injecting application to AppComponent
     */

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}

package com.example.dagger2advanced.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static String someString(){
        return "test string";
    }

    @Provides
    static boolean getApp(Application application){
        return application == null;
    }

}

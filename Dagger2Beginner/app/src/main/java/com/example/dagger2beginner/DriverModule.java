package com.example.dagger2beginner;

import com.example.dagger2beginner.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DriverModule {

    @Singleton
    @Provides
    static Driver provideDriver (){
        return new Driver();
    }

}

package com.example.dagger2beginner;

import com.example.dagger2beginner.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DriverModule {

    private String driverName;

    public DriverModule(String driverName) {
        this.driverName = driverName;
    }

    @Singleton
    @Provides
    Driver provideDriver() {
        return new Driver(driverName);
    }

}

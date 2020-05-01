package com.example.dagger2beginner;

import com.example.dagger2beginner.car.Driver;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DriverModule.class)
public interface AppComponent {

    Driver getDrive();

}

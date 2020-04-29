package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.car.Car;
import com.example.dagger2beginner.MainActivity;

import dagger.Component;

@Component (modules = {WheelsModule.class,DieselEngineModule.class})
public interface CarComponent {

    Car getCar();

    void inject(MainActivity mainActivity);

}

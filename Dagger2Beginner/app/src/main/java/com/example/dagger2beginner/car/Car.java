package com.example.dagger2beginner.car;

import android.util.Log;

import javax.inject.Inject;

public class Car {

    /** the order of usage is constructor, field, method */

    private static final String TAG = "Car";

    private Wheels wheels;
    private Driver driver;

    @Inject Engine engine; // Field injection

    @Inject
    public Car(Wheels wheels,Driver driver) { // constructor injection
        this.wheels = wheels;
        this.driver = driver;
    }

    public void enableRemote (Remote remote){ // method injection
        remote.setListener(this);
    }

    public void drive(){
        engine.start();
        Log.d(TAG,"driving..." + driver + "working to car :" + this);
    }
}

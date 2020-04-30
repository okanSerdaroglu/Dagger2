package com.example.dagger2beginner.car;

import android.util.Log;

import javax.inject.Inject;

public class DieselEngine implements Engine {
    private static final String TAG = "Car";

    private int horsePower;

    public DieselEngine(int horsePower){
        this.horsePower = horsePower;
    }

    @Override
    public void start() {
        Log.d(TAG,"diesel engine started : HorsePower " + horsePower );
    }
}

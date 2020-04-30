package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.car.DieselEngine;
import com.example.dagger2beginner.car.Engine;

import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    private int horsePower;

    public DieselEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }

    @Provides
    Engine provideEngine(){
        return new DieselEngine(horsePower);
    }

}

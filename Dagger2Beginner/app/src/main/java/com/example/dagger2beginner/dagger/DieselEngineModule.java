package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.car.DieselEngine;
import com.example.dagger2beginner.car.Engine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DieselEngineModule {

    @Binds
    abstract Engine bindEngine(DieselEngine engine);

}

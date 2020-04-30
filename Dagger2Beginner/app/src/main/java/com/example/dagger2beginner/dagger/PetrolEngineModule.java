package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.car.Engine;
import com.example.dagger2beginner.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
abstract class PetrolEngineModule {

    @Binds
    abstract Engine bindEngine(PetrolEngine engine);

}

package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.car.Rims;
import com.example.dagger2beginner.car.Tires;
import com.example.dagger2beginner.car.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
abstract class WheelsModule {

    @Provides
    static Rims provideRims(){
        return new Rims();
    }

    @Provides
    static Tires provideTires(){
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires){
        return new Wheels(rims,tires);
    }
}

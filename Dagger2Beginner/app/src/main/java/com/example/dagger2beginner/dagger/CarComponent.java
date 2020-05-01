package com.example.dagger2beginner.dagger;

import com.example.dagger2beginner.AppComponent;
import com.example.dagger2beginner.PerActivity;
import com.example.dagger2beginner.car.Car;
import com.example.dagger2beginner.MainActivity;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {WheelsModule.class, DieselEngineModule.class})
public interface CarComponent {

    Car getCar();

    void inject(MainActivity mainActivity);

    /*@Component.Builder
    interface Builder {

        @BindsInstance
        Builder horsePower(@Named("horse power") int horsePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);

        Builder appComponent(AppComponent appComponent);

        CarComponent build();
    }*/

}

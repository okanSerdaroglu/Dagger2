package com.example.dagger2beginner;

import com.example.dagger2beginner.car.Driver;
import com.example.dagger2beginner.dagger.CarComponent;
import com.example.dagger2beginner.dagger.DieselEngineModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DriverModule.class)
public interface AppComponent {

   CarComponent.Factory getActivityComponentFactory();

   @Component.Factory
   interface  Factory {
       AppComponent create(DriverModule driverModule);
   }

}

package com.example.dagger2beginner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dagger2beginner.car.Car;
import com.example.dagger2beginner.dagger.CarComponent;
import com.example.dagger2beginner.dagger.DaggerCarComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    // fields should be public for field injection
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarComponent component = DaggerCarComponent.create();
        //car = component.getCar();
        component.inject(this);
        car.drive();
    }
}

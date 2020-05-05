package com.example.dagger2advanced.di;

import com.example.dagger2advanced.di.auth.AuthModule;
import com.example.dagger2advanced.di.auth.AuthViewModelsModule;
import com.example.dagger2advanced.di.main.MainFragmentBuildersModule;
import com.example.dagger2advanced.ui.auth.AuthActivity;
import com.example.dagger2advanced.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class
                    , AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class}
    )
    abstract MainActivity contributeMainActivity();

}

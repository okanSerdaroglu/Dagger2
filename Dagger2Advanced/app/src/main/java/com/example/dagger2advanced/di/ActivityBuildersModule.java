package com.example.dagger2advanced.di;

import com.example.dagger2advanced.di.auth.AuthViewModelsModule;
import com.example.dagger2advanced.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

}

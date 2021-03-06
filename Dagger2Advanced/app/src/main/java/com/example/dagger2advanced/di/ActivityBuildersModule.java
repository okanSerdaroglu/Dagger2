package com.example.dagger2advanced.di;

import com.example.dagger2advanced.di.auth.AuthModule;
import com.example.dagger2advanced.di.auth.AuthScope;
import com.example.dagger2advanced.di.auth.AuthViewModelsModule;
import com.example.dagger2advanced.di.main.MainFragmentBuildersModule;
import com.example.dagger2advanced.di.main.MainModule;
import com.example.dagger2advanced.di.main.MainScope;
import com.example.dagger2advanced.di.main.MainViewModelsModule;
import com.example.dagger2advanced.ui.auth.AuthActivity;
import com.example.dagger2advanced.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    // Auth Component
    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class
                    , AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    // Main Component
    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class
                    , MainViewModelsModule.class
                    , MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}

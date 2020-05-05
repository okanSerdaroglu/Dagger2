package com.example.dagger2advanced.di.main;

import com.example.dagger2advanced.ui.main.posts.PostFragment;
import com.example.dagger2advanced.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();

}

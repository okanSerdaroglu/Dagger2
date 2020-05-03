package com.example.dagger2advanced.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.dagger2advanced.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider
            .Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

    /**
     * It is the same with this abstract method bindViewModelFactory,
     * however the abstract one is more efficient
    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory factory){
        return factory;
    }
    */

}

package com.example.dagger2advanced.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.di.ViewModelKey;
import com.example.dagger2advanced.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);


}

package com.example.dagger2advanced.di.main;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.di.ViewModelKey;
import com.example.dagger2advanced.ui.main.posts.PostViewModel;
import com.example.dagger2advanced.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModule(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostViewModule(PostViewModel postViewModel);


}

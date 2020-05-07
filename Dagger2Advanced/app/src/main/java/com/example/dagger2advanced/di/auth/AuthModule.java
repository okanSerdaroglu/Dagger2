package com.example.dagger2advanced.di.auth;

import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.network.auth.AuthAPI;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthAPI provideAuthAPI (Retrofit retrofit){
        return retrofit.create(AuthAPI.class);
    }

    @AuthScope
    @Provides
    @Named("auth_module")
    static User someUser() {
        return new User();
    }

}

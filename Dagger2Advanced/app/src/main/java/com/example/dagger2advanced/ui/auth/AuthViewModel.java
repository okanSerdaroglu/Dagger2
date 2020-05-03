package com.example.dagger2advanced.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.network.auth.AuthAPI;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthAPI authAPI;

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        if (authAPI == null) {
            Log.d(TAG, "auth api is null");
        } else {
            Log.d(TAG, "auth api is not null");
        }

        Log.d(TAG, " AuthViewModel: viewModel is working...");
    }

}

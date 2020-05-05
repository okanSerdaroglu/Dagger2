package com.example.dagger2advanced.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.SessionManager;
import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: viewModel is ready");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getUser();
    }

}

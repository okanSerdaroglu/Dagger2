package com.example.dagger2advanced.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthAPI authAPI;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, " AuthViewModel: viewModel is working...");

    }

    public void authenticateWithID (int userID){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authAPI.getUser(userID)
                        .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observeUser(){
        return authUser;
    }

}

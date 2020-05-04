package com.example.dagger2advanced.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.SessionManager;
import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthAPI authAPI;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthAPI authAPI, SessionManager sessionManager) {
        this.authAPI = authAPI;
        this.sessionManager = sessionManager;
        Log.d(TAG, " AuthViewModel: viewModel is working...");

    }

    public void authenticateWithID(int userID) {
        Log.d(TAG, "authenticateWithID: attempting log in");
        sessionManager.authenticateWithId(queryUserID(userID));
    }

    private LiveData<AuthResource<User>> queryUserID(int userID) {
        return LiveDataReactiveStreams.fromPublisher(
                authAPI.getUser(userID).onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                }).map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if (user.getId() == -1) {
                            return AuthResource.error("Could not authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getUser();
    }

}

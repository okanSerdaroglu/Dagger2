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

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthAPI authAPI;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, " AuthViewModel: viewModel is working...");

    }

    public void authenticateWithID(int userID) {
        authUser.setValue(AuthResource.loading((User) null));
        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
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
                            return AuthResource.error("Could not authenticate",  null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                        .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                authUser.setValue(userAuthResource);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<AuthResource<User>> observeUser() {
        return authUser;
    }

}

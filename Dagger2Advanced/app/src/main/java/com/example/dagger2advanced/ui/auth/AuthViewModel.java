package com.example.dagger2advanced.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private AuthAPI authAPI;

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, " AuthViewModel: viewModel is working...");

        authAPI.getUser(1).toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: " + user.getEmail());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}

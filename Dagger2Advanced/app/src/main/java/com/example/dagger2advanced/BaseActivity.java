package com.example.dagger2advanced;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.ui.auth.AuthActivity;
import com.example.dagger2advanced.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void navigateLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    private void subscribeObservers() {
        sessionManager.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {

                            break;
                        }
                        case AUTHENTICATED: {

                            Log.d(TAG, "onChanged: LOGIN SUCCESS : " + userAuthResource.data.getEmail());
                            break;
                        }
                        case ERROR: {

                            Log.e(TAG, "onChanged: " + userAuthResource.message);
                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            navigateLoginScreen();
                            break;
                        }

                    }
                }

            }
        });
    }
}

package com.example.dagger2advanced.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.dagger2advanced.R;
import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.ui.main.MainActivity;
import com.example.dagger2advanced.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    private EditText userID;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userID = findViewById(R.id.user_id_input);

        findViewById(R.id.login_button).setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);

        viewModel = new ViewModelProvider(this, providerFactory)
                .get(AuthViewModel.class);

        setLogo();
        subscribeObservers();
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login_button) {
            attemptLogin();
        }

    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userID.getText().toString())) {
            return;
        }
        viewModel.authenticateWithID(Integer.parseInt(userID.getText().toString()));

    }

    private void showProgressBar(boolean isShowing) {
        if (isShowing) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void subscribeObservers() {
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS : " + userAuthResource.data.getEmail());
                            onLoginSuccess();
                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this
                                    , userAuthResource.message
                                            + "\n Did you enter a number between 1 to 10 "
                                    , Toast.LENGTH_LONG).show();

                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            break;
                        }

                    }
                }

            }
        });

    }

}

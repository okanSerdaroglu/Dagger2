package com.example.dagger2advanced.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.dagger2advanced.R;
import com.example.dagger2advanced.models.User;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userID = findViewById(R.id.user_id_input);

        findViewById(R.id.login_button).setOnClickListener(this);

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

    private void subscribeObservers() {
        viewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    Log.d(TAG, "onChanged: " + user.getEmail());
                }
            }
        });
    }

}

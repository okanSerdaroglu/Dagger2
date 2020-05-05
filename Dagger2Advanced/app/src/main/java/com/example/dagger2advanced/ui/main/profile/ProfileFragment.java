package com.example.dagger2advanced.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dagger2advanced.R;
import com.example.dagger2advanced.models.User;
import com.example.dagger2advanced.ui.auth.AuthResource;
import com.example.dagger2advanced.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private ProfileViewModel profileViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TextView email;
    private TextView userName;
    private TextView website;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void subscribeObservers() {
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner()
                , new Observer<AuthResource<User>>() {
                    @Override
                    public void onChanged(AuthResource<User> userAuthResource) {
                        if (userAuthResource != null) {
                            switch (userAuthResource.status) {
                                case AUTHENTICATED: {
                                    setUserDetails(userAuthResource.data);
                                    break;
                                }
                                case ERROR: {
                                    setErrorDetails(userAuthResource.message);
                                    break;
                                }
                            }

                        }
                    }
                });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        userName.setText("error");
        website.setText("error");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        userName.setText(data.getUsername());
        website.setText(data.getWebsite());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: Profile fragment created...");
        profileViewModel = new ViewModelProvider(this, providerFactory)
                .get(ProfileViewModel.class);
        email = view.findViewById(R.id.email);
        userName = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        subscribeObservers();
    }
}

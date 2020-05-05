package com.example.dagger2advanced.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dagger2advanced.SessionManager;
import com.example.dagger2advanced.network.main.MainApi;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";

    // inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostViewModel(SessionManager sessionManager,MainApi mainApi){
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "PostViewModel: viewModel is working ...");

    }

}

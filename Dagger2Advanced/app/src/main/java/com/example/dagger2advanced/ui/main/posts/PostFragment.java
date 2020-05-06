package com.example.dagger2advanced.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2advanced.R;
import com.example.dagger2advanced.models.Post;
import com.example.dagger2advanced.ui.main.Resource;
import com.example.dagger2advanced.util.VerticalSpacingItemDecoration;
import com.example.dagger2advanced.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    private PostViewModel postViewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postViewModel = new ViewModelProvider(this, viewModelProviderFactory)
                .get(PostViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        postViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status){
                        case LOADING: {
                            Log.d(TAG, "onChanged: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            Log.d(TAG, "onChanged: got posts...");
                            postsRecyclerAdapter.setPosts(listResource.data);
                            break;
                        }

                        case ERROR:{
                            Log.e(TAG, "onChanged: ERROR" + listResource.message );
                            break;
                        }

                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }

}

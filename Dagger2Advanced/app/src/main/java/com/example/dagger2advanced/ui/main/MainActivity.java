package com.example.dagger2advanced.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dagger2advanced.BaseActivity;
import com.example.dagger2advanced.R;
import com.example.dagger2advanced.ui.main.posts.PostFragment;
import com.example.dagger2advanced.ui.main.profile.ProfileFragment;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFragment();
    }

    private void testFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new PostFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            sessionManager.logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

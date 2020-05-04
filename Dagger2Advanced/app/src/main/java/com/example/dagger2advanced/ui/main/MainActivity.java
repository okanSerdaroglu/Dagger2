package com.example.dagger2advanced.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.dagger2advanced.BaseActivity;
import com.example.dagger2advanced.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

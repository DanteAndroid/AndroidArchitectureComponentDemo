package com.danteandroid.aacdemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.danteandroid.aacdemo.R;
import com.danteandroid.aacdemo.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String name="test";
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        final EditText editText = findViewById(R.id.userName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: ");
                name = s.toString();
                viewModel.setUserName(name);
            }
        });
        show(null);
    }

    public void show(View view) {
        Fragment fragment = UserFragment.newInstance(name);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("user")
                .replace(R.id.container, fragment, null)
                .commit();
    }
}

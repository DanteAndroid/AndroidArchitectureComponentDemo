package com.danteandroid.aacdemo.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.danteandroid.aacdemo.R;
import com.danteandroid.aacdemo.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String name = "test";
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
                name = s.toString();
                viewModel.setUserName(name);
            }
        });

        if (savedInstanceState == null) {
            show(null);
        }
    }

    public void show(View view) {
        Fragment fragment = UserFragment.newInstance(name);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("user")
                .replace(R.id.container, fragment, null)
                .commit();
    }
}

package com.fantsen.walnut.input.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fantsen.walnut.softinput.WalnutInputActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onInputClick(View v) {
        Intent intent = new Intent(this, WalnutInputActivity.class);
        startActivity(intent);
    }
}

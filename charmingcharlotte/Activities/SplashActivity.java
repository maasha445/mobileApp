package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.mad.charmingcharlotte.R;
import com.mad.charmingcharlotte.SharedPrefUtility;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPrefUtility.init(getApplicationContext());
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();

    }
}
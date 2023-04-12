package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.mad.charmingcharlotte.R;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mad.charmingcharlotte.Adapters.ViewPagerAdapter;
import com.mad.charmingcharlotte.Fragments.HistoryFragment;
import com.mad.charmingcharlotte.Fragments.ProfileFragment;

public class MyAccountActivity extends AppCompatActivity {

    private TabLayout tabLayoutAccount;
    private ViewPager viewPagerAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tabLayoutAccount = findViewById(R.id.tab_layout_account);
        viewPagerAccount = findViewById(R.id.view_pager_account);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding fragments
        adapter.AddFragment(new ProfileFragment(), "Profile");
        adapter.AddFragment(new HistoryFragment(), "Purchase History");

        //Adapter setup
        viewPagerAccount.setAdapter(adapter);
        tabLayoutAccount.setupWithViewPager(viewPagerAccount);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Account");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
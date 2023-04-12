package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mad.charmingcharlotte.Adapters.ViewPagerAdapter;
import com.mad.charmingcharlotte.R;
import com.mad.charmingcharlotte.SharedPrefUtility;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.view_pager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding fragments
        adapter.AddFragment(new WomensCategoriesFrag(), "Women");
        adapter.AddFragment(new MensCategoriesFrag(), "Men");
        adapter.AddFragment(new NewArrivalsFragment(), "New Arrivals");

        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        FloatingActionButton cart = (FloatingActionButton) findViewById(R.id.fab);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTxtView = headerView.findViewById(R.id.username);
        String userEmail = SharedPrefUtility.read(SharedPrefUtility.EMAIL, null);
        userNameTxtView.setText(userEmail);
        CircleImageView profileImgView = headerView.findViewById(R.id.profile_image);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        ComponentName componentName = new ComponentName(getBaseContext(), SearchResultActivity.class);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_account) {
            Intent intent = new Intent(HomeActivity.this, MyAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_wish_list) {
            Intent intent = new Intent(HomeActivity.this, WishlistActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_faq) {
            Intent intent = new Intent(HomeActivity.this, FAQActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact_us) {
            Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sign_out) {
            SharedPrefUtility.write(SharedPrefUtility.EMAIL, null);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

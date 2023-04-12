package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mad.charmingcharlotte.R;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.mad.charmingcharlotte.Adapters.ItemsRecyclerAdapter;
import com.mad.charmingcharlotte.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Result");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            List<Item> items = Item.listAll(Item.class);
            List<Item> lstItem = new ArrayList<>();

            for (Item item : items) {
                String search = item.getName();
                String[] arr = search.split(" ");
                if ((search.toUpperCase()).contains(query.toUpperCase())) {
                    lstItem.add(item);
                }
            }
            RecyclerView recyclerView = findViewById(R.id.search_rview_id);
            ItemsRecyclerAdapter myAdapter = new ItemsRecyclerAdapter(this, lstItem);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(myAdapter);
        }
    }
}
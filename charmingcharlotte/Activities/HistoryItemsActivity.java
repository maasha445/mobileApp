package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.mad.charmingcharlotte.Adapters.PurchaseRecyclerAdapter;
import com.mad.charmingcharlotte.Models.CartItem;
import com.mad.charmingcharlotte.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_items);

        Intent intent = getIntent();
        String cartID = intent.getStringExtra("cartID");

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cart ID: " + cartID);
        setSupportActionBar(toolbar);

        final List<CartItem> lst = new ArrayList<>();

        List<CartItem> cartitems = CartItem.findWithQuery(CartItem.class, "Select * from CART_ITEM where  cart = ?", cartID);
        for (CartItem item : cartitems) {
            lst.add(item);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_purchased_items);
        PurchaseRecyclerAdapter myAdapter = new PurchaseRecyclerAdapter(this, lst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
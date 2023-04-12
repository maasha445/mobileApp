package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mad.charmingcharlotte.R;
import android.os.Bundle;
import android.widget.Toast;

import com.mad.charmingcharlotte.Adapters.ItemsRecyclerAdapter;
import com.mad.charmingcharlotte.Models.Item;
import com.mad.charmingcharlotte.Models.User;
import com.mad.charmingcharlotte.Models.WishlistItem;
import com.mad.charmingcharlotte.SharedPrefUtility;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    List<Item> lstItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Wishlist");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        String userEmail = SharedPrefUtility.read(SharedPrefUtility.EMAIL, null);
        List<User> users = User.findWithQuery(User.class, "Select * from User where email = ? ", userEmail);
        for (User user : users) {
            long uid = user.getId();
            List<WishlistItem> wishlist = WishlistItem.listAll(WishlistItem.class);
            if (wishlist.size() > 0) {
                lstItem = new ArrayList<>();
                for (WishlistItem wishitem : wishlist) {
                    long wishUID = wishitem.getUser().getId();
                    if (uid == wishUID) {
                        lstItem.add(wishitem.getItem());
                    } else {
                        Toast.makeText(this, "No Items in your Wishlist", Toast.LENGTH_SHORT).show();
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recycler_view_wishlist);
                ItemsRecyclerAdapter myAdapter = new ItemsRecyclerAdapter(this, lstItem);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(myAdapter);

            } else {
                Toast.makeText(this, "No Items in your Wishlist", Toast.LENGTH_SHORT).show();
            }

        }


    }
}
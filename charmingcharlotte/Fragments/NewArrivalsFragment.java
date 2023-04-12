package com.mad.charmingcharlotte.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.charmingcharlotte.Models.Item;
import com.mad.charmingcharlotte.R;

import java.util.ArrayList;
import java.util.List;


public class NewArrivalsFragment extends Fragment {

    List<Item> lstItem;

    public NewArrivalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_arrivals, container, false);

        //get a reference to recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_id1);

        //set layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<Item> items = Item.listAll(Item.class);

        lstItem = new ArrayList<>();

        for (Item item : items) {

            if (item.isNewArrival()) {
                lstItem.add(item);
            }
        }

        //create an instance of ItemsRecyclerAdapter
        ItemsRecyclerAdapter myAdapter = new ItemsRecyclerAdapter(getActivity(), lstItem);

        //set adapter
        recyclerView.setAdapter(myAdapter);

        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
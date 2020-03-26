package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.ourproducts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.alicearmstrong.coffeysloyaltyprojectv1.ExpandableListAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PorkFragment extends Fragment
{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_pork, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpPork);
        listView = (ListView) view.findViewById(R.id.lvPork);
        porkProducts();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



        return view;
    }

    // Method to generate the FAQ questions
    private void porkProducts()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding header data
        listDataHeader.add("Sausages");
        listDataHeader.add("Burgers");
        listDataHeader.add("Pork Chops");
        listDataHeader.add("Bacon");
        listDataHeader.add("Ribs");


        // Adding child data

        List<String> sausages = new ArrayList<String>();
        sausages.add("Pork Sausages \n" +
                "Pork Cocktail Sausages \n" +
                "Pork & Crack Black Pepper Sausages \n" +
                "Pork & Chili Sausages \n" +
                "Pork & Leak Sausages \n" +
                "Pork & Honey Sausages \n" +
                "Pork & Chocolate Sausages \n" +
                "Pork & Caramelised Red Onion & Garlic Sausages \n" +
                "Pork Cumberland Sausages \n" +
                "Pork & Bramley Apple Sausages \n" +
                "Pork Sausages (Foot Long)");

        List<String> burgers = new ArrayList<String>();
        burgers.add("4oz Pork Burgers \n" +
                "6oz Pork Burgers \n" +
                "4oz Pork & Apple Burgers");

        List<String> chops = new ArrayList<String>();
        chops.add("BBQ Glaze Pork Chops \n" +
                "Peppered Glaze Pork Chops \n" +
                "Piri Piri Glaze Pork Chops \n" +
                "Sweet Chili Glaze Pork Chops \n" +
                "Chinese Glaze Pork Chops");

        List<String> bacon = new ArrayList<String>();
        bacon.add("Back Bacon \n" +
                "Smoked Back Bacon \n" +
                "Middle Bacon \n" +
                "Streaky Bacon \n" +
                "Bacon Chops");

        List<String> ribs = new ArrayList<String>();
        ribs.add("Pork Ribs \n" +
                "BBQ Glaze Pork Ribs \n" +
                "Piri Piri Glaze Pork Chops \n" +
                "Sweet Chili Glaze Pork Chops \n" +
                "Chinese Glaze Pork Chops");



        listDataChild.put(listDataHeader.get(0), sausages);
        listDataChild.put(listDataHeader.get(1), burgers);
        listDataChild.put(listDataHeader.get(2), chops);
        listDataChild.put(listDataHeader.get(3), bacon);
        listDataChild.put(listDataHeader.get(4), ribs);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Pork Pieces");
        arrayList.add("Pork Shoulder");
        arrayList.add("Pork Belly");
        arrayList.add("Pork Fillet");
        arrayList.add("Pork Loin");
        arrayList.add("Pork Sausage Meat");
        arrayList.add("Gammon Joint");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);



    }


}

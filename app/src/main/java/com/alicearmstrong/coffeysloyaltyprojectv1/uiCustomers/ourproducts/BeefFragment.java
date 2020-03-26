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


// http://www.apnatutorials.com/android/expandable-listview-customization-and-usage.php?categoryId=2&subCategoryId=57&myPath=android/expandable-listview-customization-and-usage.php
// look into adding images as well to expandable list view
public class BeefFragment extends Fragment
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
        View view = inflater.inflate( R.layout.fragment_beef, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpBeef);
        listView = (ListView) view.findViewById(R.id.lvBeef);

        beefProducts();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



        return view;
    }

    // Method to generate the FAQ questions
    private void beefProducts()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding header data
        listDataHeader.add("Steaks");
        listDataHeader.add("Sausages");
        listDataHeader.add("Burgers");


        // Adding child data
        List<String> steaks = new ArrayList<String>();
        steaks.add("Sirloin Steak \n" +
                "Ribeye Steak \n" +
                "Fillet Steak \n" +
                "T-Bone Steak \n" +
                "Chump Steak \n" +
                "Braising Steak \n" +
                "Frying Steak");

        List<String> sausages = new ArrayList<String>();
        sausages.add("Steak Sausages \n" +
                "Steak Cocktail Sausages \n" +
                "Steak & Crack Black Pepper Sausages \n" +
                "Steak & Chili Sausages \n" +
                "Steak Sausages with Guinness");

        List<String> burgers = new ArrayList<String>();
        burgers.add("4oz Steak Burgers \n" +
                "6oz Steak Burgers \n" +
                "8oz Steak Burgers");

        listDataChild.put(listDataHeader.get(0), steaks);
        listDataChild.put(listDataHeader.get(1), sausages);
        listDataChild.put(listDataHeader.get(2), burgers);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Eye of Silverside Roast");
        arrayList.add("Silverside Roast");
        arrayList.add("95% Lean Steak Mince");
        arrayList.add("Steak Pieces");
        arrayList.add("Beef Vegetable Roll");
        arrayList.add("Beef Meatballs");
        arrayList.add("Steak Kebabs (Various Seasoning Available)");
        arrayList.add("Beef Cheeks");
        arrayList.add("Beef Brisket (Bone in / Bone Out)");
        arrayList.add("Chuck");
        arrayList.add("Topside");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);


    }


}

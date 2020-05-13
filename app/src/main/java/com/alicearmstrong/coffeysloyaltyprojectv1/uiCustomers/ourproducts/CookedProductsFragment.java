package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.ourproducts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.ExpandableListAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CookedProductsFragment extends Fragment
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
        View view = inflater.inflate( R.layout.fragment_cooked_products, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpCookedMeats);
        listView = (ListView) view.findViewById(R.id.lvCookedMeats);

        poultryProducts();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



        return view;
    }

    // Method to generate the FAQ questions
    private void poultryProducts()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding header data
        listDataHeader.add("Pies");
        listDataHeader.add("Pasta");
        listDataHeader.add("Side Dishes");
        listDataHeader.add("Sweet Treats");


        // Adding child data
        List<String> pies = new ArrayList<String>();
        pies.add("Chicken & Ham Pie\n" +
                "Chicken & Vegetable Pie\n" +
                "Mince & Onion Pie \n" +
                "Steak Pie \n" +
                "Pork Pie");

        List<String> pasta = new ArrayList<String>();
        pasta.add("Lasagna \n" +
                "Chicken & Tomato Pasta \n" +
                "Carbonara");

        List<String> sideDishes = new ArrayList<String>();
        sideDishes.add("Creamy Mashed Potato \n" +
                "Champ \n" +
                "Carrot & Parsnip Mash");


        List<String> sweetTreats = new ArrayList<String>();
        sweetTreats.add("Apple Pie \n" +
                "Rhubarb Pie\n" +
                "Apple Crumble \n" +
                "Rhubarb Crumble \n" +
                "Shortbread \n" +
                "Chocolate Shortbread \n" +
                "Flakemeal Biscuits \n" +
                "Fifteens \n" +
                "German Biscuits");

        listDataChild.put(listDataHeader.get(0), pies);
        listDataChild.put(listDataHeader.get(1), pasta);
        listDataChild.put(listDataHeader.get(2), sideDishes);
        listDataChild.put(listDataHeader.get(3), sweetTreats);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Homemade Stew");
        arrayList.add("Chicken & Vegetable Soup");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);

    }


}

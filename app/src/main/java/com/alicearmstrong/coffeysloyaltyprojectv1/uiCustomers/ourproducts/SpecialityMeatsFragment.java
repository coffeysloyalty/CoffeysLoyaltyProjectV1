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

public class SpecialityMeatsFragment extends Fragment
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
        View view = inflater.inflate( R.layout.fragment_speciality_meats, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpSpecialityMeats);
        listView = (ListView) view.findViewById(R.id.lvSpecialityMeats);
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
        listDataHeader.add("Venison");
        listDataHeader.add("Poultry");

        // Adding child data

        List<String> venison = new ArrayList<String>();
        venison.add("Venison Joint\n" +
                "Venison Steaks \n" +
                "Diced Venison Pieces \n" +
                "Venison Sausages \n" +
                "6oz Venison Burgers");

        List<String> birds = new ArrayList<String>();
        birds.add("Pheasant \n" +
                "Quail \n" +
                "Goose \n" +
                "Guinea Fowl \n" +
                "Duck");

        listDataChild.put(listDataHeader.get(0), venison);
        listDataChild.put(listDataHeader.get(1), birds);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Haggis");
        arrayList.add("Rabbit");
        arrayList.add("Black / White Pudding");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);



    }


}

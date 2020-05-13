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

public class PoultryFragment extends Fragment
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
        View view = inflater.inflate(R.layout.fragment_poultry, container, false);
        expListView = (ExpandableListView) view.findViewById( R.id.lvExpPoultry);
        listView = (ListView) view.findViewById(R.id.lvPoultry);

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
        listDataHeader.add("Chicken Fillets");
        listDataHeader.add("Whole Chickens");
        listDataHeader.add("Wings");
        listDataHeader.add("Frozen Items");
        listDataHeader.add("Turkey");


        // Adding child data
        List<String> fillets = new ArrayList<String>();
        fillets.add("Chicken Fillets\n" +
                "Diced Chicken Fillets \n" +
                "BBQ Glaze Chicken FIllets \n" +
                "Chinese Glaze Chicken Fillets\n" +
                "Sweet Chili Glaze Chicken Fillets \n" +
                "Piri Piri Glaze Chicken Fillets");

        List<String> wholeChickens = new ArrayList<String>();
        wholeChickens.add("1.4KG Whole Chicken \n" +
                "2KG Whole Chicken \n" +
                "5KG Whole Chicken");

        List<String> wings = new ArrayList<String>();
        wings.add("Chicken Wings \n" +
                "BBQ Glaze Chicken Wings \n" +
                "Chinese Glaze Chicken Wings \n" +
                "Sweet Chili Glaze Chicken Wings \n" +
                "Piri Piri Glaze Chicken Wings");

        List<String> frozen = new ArrayList<String>();
        frozen.add("Breaded Chicken Goujons \n" +
                "Southern Fried Chicken Goujons \n" +
                "Salt & Chili Chicken Goujons \n" +
                "Battered Chicken Goujons \n" +
                "Battered Chicken Nuggets \n" +
                "Breaded Chicken Nuggets \n" +
                "Popcorn Chicken\n" +
                "Breaded Chicken Kiev \n" +
                "Breaded Creamy Peppered Chicken\n" +
                "Breaded Sweet Chili Chicken \n" +
                "Breaded Chicken Maryland \n" +
                "Chicken Satay Sticks");

        List<String> turkey = new ArrayList<String>();
        turkey.add("Turkey Fillet \n" +
                "Whole Turkey\n" +
                "Turkey Mince \n" +
                "Turkey Sausages \n" +
                "Diced Turkey Breast \n" +
                "Turkey Legs");

        listDataChild.put(listDataHeader.get(0), fillets);
        listDataChild.put(listDataHeader.get(1), wholeChickens);
        listDataChild.put(listDataHeader.get(2), wings);
        listDataChild.put(listDataHeader.get(3), frozen);
        listDataChild.put(listDataHeader.get(4), turkey);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Chicken Thighs");
        arrayList.add("Chicken Mince");
        arrayList.add("Chicken Sausages");
        arrayList.add("Chicken Drumsticks");
        arrayList.add("Chicken Kebabs (Various Seasoning Available)");
        arrayList.add("Chicken Liver");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);

    }


}

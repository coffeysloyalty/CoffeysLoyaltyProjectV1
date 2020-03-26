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

public class LambFragment extends Fragment
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
        View view = inflater.inflate( R.layout.fragment_lamb, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpLamb);
        listView = (ListView) view.findViewById(R.id.lvLamb);

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
        listDataHeader.add("Shoulder");
        listDataHeader.add("Leg");
        listDataHeader.add("Shank");

        // Adding child data
        List<String> shoulder = new ArrayList<String>();
        shoulder.add("Lamb Shoulder\n" +
                "Lamb Shoulder Chops\n" +
                "Diced Lamb Shoulder Pieces\n" +
                "Lamb Shoulder Joint ");

        List<String> leg = new ArrayList<String>();
        leg.add("Leg Of Lamb \n" +
                "Half Leg of Lamb \n" +
                "Diced Lamb Leg Pieces\n" +
                "Lamb Leg Steaks");

        List<String> shank = new ArrayList<String>();
        shank.add("Lamb Shank \n" +
                "Lamb Shank in Mint Gravy\n" +
                "Lamb Shank with Roasted Vegetables");

        listDataChild.put(listDataHeader.get(0), shoulder);
        listDataChild.put(listDataHeader.get(1), leg);
        listDataChild.put(listDataHeader.get(2), shank);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Lamb Neck Fillets");
        arrayList.add("Lamb Mince");
        arrayList.add("6oz Lamb Burger");
        arrayList.add("Lamb Liver");
        arrayList.add("Rack of Lamb");
        arrayList.add("Lamb Kidney");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_view_layout, R.id.textView2, arrayList);
        listView.setAdapter(arrayAdapter);

    }


}

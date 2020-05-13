package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.faq;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.ExpandableListAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_faq_customer, container, false);
        expListView = (ExpandableListView) root.findViewById(R.id.lvExp);
        faqQuestions();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        return root;
    }

    // Method to generate the FAQ questions
    private void faqQuestions()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header data
        listDataHeader.add("What are your opening hours?");
        listDataHeader.add("How much does ... cost?");
        listDataHeader.add("Can I purchase a gift voucher?");
        listDataHeader.add("What are your contact details?");
        listDataHeader.add("Do you often gluten free alternatives?");
        listDataHeader.add("Can I pre-order products for collection in store?");
        listDataHeader.add("Is there a minimum spend required to collect a stamp?");
        listDataHeader.add("Do you have ... in stock?");


        // Adding child data
        List<String> openinghours = new ArrayList<String>();
        openinghours.add("Monday: 7:30am - 6:00pm \n" +
                         "Tuesday: 7:30am - 6:00pm \n" +
                        "Wednesday: 7:30am - 6:00pm \n" +
                        "Thursday: 7:30am - 6:00pm \n" +
                        "Friday: 7:30am - 6:00pm \n" +
                        "Saturday: 7:00am - 5:30pm \n" +
                        "Sunday: Closed");

        List<String> cost = new ArrayList<String>();
        cost.add("Please contact us directly to confirm the price of a current item. With all of our prices changing on a regular basis " +
                "contacting us will confirm the most recent price. Please call into store or use the chat feature with this application!");


        List<String> giftVouchers = new ArrayList<String>();
        giftVouchers.add("We have gift vouchers available to purchase in store for any specified amount. Please call into store and talk to " +
                "a member of staff who will be happy to assist you.");

        List<String> contactDetails = new ArrayList<String>();
        contactDetails.add("You can find us at: \n" +
                            "380 Lisburn Road, \n" +
                            "Belfast, \n" +
                            "BT9 6GL\n" +
                            "Check out our location section of the app to see where we are situated!\n\n" +
                            "If you prefer to give us a call, you can reach us on: \n" +
                            "028 9066 6292");

        List<String> gluten = new ArrayList<>();
        gluten.add("We offer some gluten free alternatives, for example Gluten Free Burgers. Please contact us directly to obtain a more updated list of our " +
                "gluten free products. Please call into store or use the chat feature with this application!");

        List<String> preorder = new ArrayList<>();
        preorder.add("Yes, we do take pre-orders from our customers. We require at least 2 working days for orders. For larger orders we do require further notice." +
                "We require 4 working days for larger orders.");

        List<String> minimumSpend = new ArrayList<>();
        minimumSpend.add("Yes, the minimum spend required for a purchase to obtain a stamp is £5.");

        List<String> inStock = new ArrayList<>();
        inStock.add("Please contact us directly to confirm the stock of a current item. You can look at our products page " +
                "within the app to see our wide range of products. Please call into store or use the chat feature with this application!");

        listDataChild.put(listDataHeader.get(0), openinghours);
        listDataChild.put(listDataHeader.get(1), cost);
        listDataChild.put(listDataHeader.get(2), giftVouchers);
        listDataChild.put(listDataHeader.get(3), contactDetails);
        listDataChild.put(listDataHeader.get(4), gluten);
        listDataChild.put(listDataHeader.get(5), preorder);
        listDataChild.put(listDataHeader.get(6), minimumSpend);
        listDataChild.put(listDataHeader.get(7), inStock);
    }


}
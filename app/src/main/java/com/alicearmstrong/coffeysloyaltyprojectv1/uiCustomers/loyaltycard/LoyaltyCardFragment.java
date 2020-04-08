package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.loyaltycard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoyaltyCardFragment extends Fragment
{

     DatabaseReference databaseReference;
     FirebaseAuth firebaseAuth;
     String userID;
     ImageView Stamp1, Stamp2, Stamp3, Stamp4, Stamp5, Stamp6, Stamp7, Stamp8, Stamp9, Stamp10;
     ListView listView;
     ArrayList<String> arrayList = new ArrayList<>();
     ArrayAdapter<String> arrayAdapter;


    private LoyaltyCardViewModel loyaltyCardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        loyaltyCardViewModel = ViewModelProviders.of(this).get(LoyaltyCardViewModel.class);
        View root = inflater.inflate( R.layout.fragment_loyalty_card_customers, container, false);
        Stamp1 = root.findViewById(R.id.ivStamp1);
        Stamp2 = root.findViewById(R.id.ivStamp2);
        Stamp3 = root.findViewById(R.id.ivStamp3);
        Stamp4 = root.findViewById(R.id.ivStamp4);
        Stamp5 = root.findViewById(R.id.ivStamp5);
        Stamp6 = root.findViewById(R.id.ivStamp6);
        Stamp7 = root.findViewById(R.id.ivStamp7);
        Stamp8 = root.findViewById(R.id.ivStamp8);
        Stamp9 = root.findViewById(R.id.ivStamp9);
        Stamp10 = root.findViewById(R.id.ivStamp10);
        listView = root.findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        showLoyaltyPoints();
        loyaltyCardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                boolean historyExists = dataSnapshot.child("History").exists();

                int count;

                for(count = 1; count<=10; count++ )
                {
                    final boolean date = dataSnapshot.child("History").child(String.valueOf(count)).exists();

                    if (date == true)
                    {
                        final String stampDate = dataSnapshot.child("History").child(String.valueOf(count)).getValue().toString();

                        arrayList.add(
                                "Stamp: " + count + "\n" +
                                        "Date: " + stampDate);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

                if(historyExists == false)
                {
                    arrayList.add("Shop in-store and scan your QR Code to collect some stamps!") ;
                    arrayAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return root;
    }


    public void showLoyaltyPoints()
    {
        //Get users loyalty points score

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve users loyalty points score
                String loyaltyPoints = dataSnapshot.child("loyaltyScore").getValue().toString();

                switch (loyaltyPoints) {
                    case "0":
                        break;


                    case "1":
                        Stamp1.setVisibility(View.VISIBLE);
                        break;

                    case "2":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        break;

                    case "3":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        break;

                    case "4":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        break;

                    case "5":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        break;


                    case "6":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        Stamp6.setVisibility(View.VISIBLE);
                        break;


                    case "7":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        Stamp6.setVisibility(View.VISIBLE);
                        Stamp7.setVisibility(View.VISIBLE);
                        break;

                    case "8":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        Stamp6.setVisibility(View.VISIBLE);
                        Stamp7.setVisibility(View.VISIBLE);
                        Stamp8.setVisibility(View.VISIBLE);
                        break;

                    case "9":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        Stamp6.setVisibility(View.VISIBLE);
                        Stamp7.setVisibility(View.VISIBLE);
                        Stamp8.setVisibility(View.VISIBLE);
                        Stamp9.setVisibility(View.VISIBLE);
                        break;


                    case "10":
                        Stamp1.setVisibility(View.VISIBLE);
                        Stamp2.setVisibility(View.VISIBLE);
                        Stamp3.setVisibility(View.VISIBLE);
                        Stamp4.setVisibility(View.VISIBLE);
                        Stamp5.setVisibility(View.VISIBLE);
                        Stamp6.setVisibility(View.VISIBLE);
                        Stamp7.setVisibility(View.VISIBLE);
                        Stamp8.setVisibility(View.VISIBLE);
                        Stamp9.setVisibility(View.VISIBLE);
                        Stamp10.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });

    }


}






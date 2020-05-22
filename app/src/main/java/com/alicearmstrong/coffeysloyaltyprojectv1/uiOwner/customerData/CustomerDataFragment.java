package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.customerData;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomerDataFragment extends Fragment
{

    DatabaseReference databaseReference;
    ListView listView;
    EditText etSearch;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter <String> arrayAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_customer_data, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers");
        listView = root.findViewById(R.id.listView);
        etSearch = root.findViewById(R.id.etSearch);
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        //Search bar to allow owner to search for customer
        etSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                (CustomerDataFragment.this).arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });



        databaseReference.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                // Retrieve information from database
                final String firstName = dataSnapshot.child("firstName").getValue().toString();
                final String surname = dataSnapshot.child("surname").getValue().toString();
                final String dob = dataSnapshot.child("dob").getValue().toString();
                final String contactNumber = dataSnapshot.child("contactNumber").getValue().toString();
                final String email = dataSnapshot.child("email").getValue().toString();

                arrayList.add(firstName + " " + surname + "\n \n" +
                        "Email: " + email + "\n" +
                        "Contact Number: " + contactNumber + "\n" +
                        "Date of Birth: " + dob + "\n");
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                // Logging
                Log.d("CustomerDataFragment","The read failed: " + databaseError.getCode());
            }

        });

        return root;

    }

}
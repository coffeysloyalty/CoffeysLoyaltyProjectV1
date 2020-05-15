package com.alicearmstrong.coffeysloyaltyprojectv1.ChatFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.CustomerAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Customers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomerAdapter customerAdapter;
    private List<Customers> customersList;
    DatabaseReference databaseReference;

    EditText etSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_users , container, false);
        etSearch = view.findViewById(R.id.etSearch);
        recyclerView = view.findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );

        customersList = new ArrayList<>(  );

        readCustomers();

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
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        return view;
    }

    // Method for reading customers
    private void readCustomers()
    {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers");

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                customersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Customers customers = snapshot.getValue(Customers.class);

                    if (!customers.getId().equals(firebaseUser.getUid()))
                    {
                        customersList.add( customers );
                    }
                }

                customerAdapter = new CustomerAdapter( getContext(), customersList );
                recyclerView.setAdapter( customerAdapter );

            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {

            }
        } );
    }
}

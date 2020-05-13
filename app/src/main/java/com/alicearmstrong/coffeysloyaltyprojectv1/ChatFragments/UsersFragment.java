package com.alicearmstrong.coffeysloyaltyprojectv1.ChatFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_users , container, false);

        recyclerView = view.findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );

        customersList = new ArrayList<>(  );

        readCustomers();

        return view;
    }

    // Method for reading customers
    private void readCustomers()
    {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customers");

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

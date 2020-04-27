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
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Chat;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Customers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatsFragment extends Fragment
{

    private RecyclerView recyclerView;

    private CustomerAdapter customerAdapter;
    private List<Customers> mCustomers;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    private List<String> customerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_chats, container,false );

        recyclerView = view.findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(new LinearLayoutManager( getContext() ) );

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        customerList = new ArrayList<>(  );

        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerList.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Chat chat=snapshot.getValue(Chat.class);

                    if (chat.getSender().equals(firebaseUser.getUid())) {

                        customerList.add(chat.getReceiver());
                    }

                    if (chat.getReceiver().equals(firebaseUser.getUid()))
                    {
                        customerList.add(chat.getSender());
                    }

                }
                Set<String> hashSet = new HashSet<String>(customerList);

                customerList.clear();
                customerList.addAll(hashSet);

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        return view;
    }

    private void readChats()
    {
        mCustomers = new ArrayList<>(  );

        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCustomers.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Customers customer = snapshot.getValue(Customers.class);

                    for(String id:customerList)
                    {
                        assert customer != null;

                        if (customer.getId().equals(id))
                        {
                            mCustomers.add(customer);
                        }
                    }
                }


                customerAdapter = new CustomerAdapter(getContext(),mCustomers);

                recyclerView.setAdapter(customerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}

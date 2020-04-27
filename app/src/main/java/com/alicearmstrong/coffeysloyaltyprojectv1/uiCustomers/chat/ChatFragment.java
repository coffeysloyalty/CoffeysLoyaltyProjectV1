package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.chat;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.MessageAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.MessageActivityOwner;
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
import java.util.HashMap;
import java.util.List;


public class ChatFragment extends Fragment {


    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    String userid ;
    ImageButton btnSend;
    EditText etTextSend;

    MessageAdapter messageAdapter;
    List<Chat> mChat;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_chat_customer, container, false);

        btnSend = root.findViewById( R.id.btn_send );
        etTextSend = root.findViewById( R.id.text_send );

        userid = "lYr5teVjoFP7TkXPowgARdDBzV83";

        recyclerView = root.findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        recyclerView.setLayoutManager( linearLayoutManager );

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers").child( userid );

        btnSend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etTextSend.getText().toString();
                if(!message.equals( "" ))
                {
                    sendMessage(firebaseUser.getUid(), message );
                    etTextSend.setText( "" );
                }
                else
                {
                    Toast.makeText( getContext(), "You cannot send empty message.", Toast.LENGTH_SHORT).show();
                }

            }
        } );

        databaseReference.addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                readMessage( firebaseUser.getUid(), userid );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        return root;
    }

    public void sendMessage(String sender,  String message)
    {
        String receiver = "lYr5teVjoFP7TkXPowgARdDBzV83";

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>( );
        hashMap.put("sender", sender);
        hashMap.put( "receiver", receiver );
        hashMap.put( "message", message );

        databaseReference.child( "Chats" ).push().setValue( hashMap );

    }

    private void readMessage(final String myid, final String userid)
    {
        mChat = new ArrayList<>(  );

        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();

                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals( myid ) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid)  && chat.getSender().equals(myid))
                    {
                        mChat.add( chat );
                    }

                    messageAdapter = new MessageAdapter(getContext(), mChat);
                    recyclerView.setAdapter( messageAdapter );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
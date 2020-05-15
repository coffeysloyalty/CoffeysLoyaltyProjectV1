package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.MessageAdapter;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Chat;
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
    List<Chat> chatList;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_chat_customer, container, false);

        btnSend = root.findViewById( R.id.btn_send );
        etTextSend = root.findViewById( R.id.text_send );
        recyclerView = root.findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        recyclerView.setLayoutManager( linearLayoutManager );

        userid = "lYr5teVjoFP7TkXPowgARdDBzV83";
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers").child( userid );

        btnSend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etTextSend.getText().toString();
                // Validation for empty message
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

    // Method for send message
    public void sendMessage(String senderID,  String message)
    {
        String receiverID = "lYr5teVjoFP7TkXPowgARdDBzV83";


            databaseReference = FirebaseDatabase.getInstance().getReference();
            Chat messageDetails = new Chat( senderID,receiverID,message );
            databaseReference.child("Chats").push().setValue( messageDetails );


    }

    // Method for reading message
    private void readMessage(final String senderID, final String receiverID)
    {
        chatList = new ArrayList<>(  );

        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatList.clear();

                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals( senderID ) && chat.getSender().equals(receiverID) || chat.getReceiver().equals(receiverID)  && chat.getSender().equals(senderID))
                    {
                        chatList.add( chat );
                    }

                    messageAdapter = new MessageAdapter(getContext(), chatList);
                    recyclerView.setAdapter( messageAdapter );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
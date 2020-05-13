package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.alicearmstrong.coffeysloyaltyprojectv1.Adapter.MessageAdapter;
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

public class MessageActivityOwner extends AppCompatActivity {

    TextView customerName;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ImageButton btnSend;
    EditText etTextSend;
    MessageAdapter messageAdapter;
    List<Chat> chatList;
    RecyclerView recyclerView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_message_owner );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        customerName = findViewById( R.id.customerName );
        btnSend = findViewById( R.id.btn_send );
        etTextSend = findViewById( R.id.text_send );

        recyclerView = findViewById( R.id.recycler_view );
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( linearLayoutManager );

        intent = getIntent();
        final String userid = intent.getStringExtra( "userid" );

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers").child( userid );

        // OnClick for send message
        btnSend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etTextSend.getText().toString();
                // Validation for empty message
                if(!message.equals( "" ))
                {
                    sendMessage(firebaseUser.getUid(), userid, message );
                    etTextSend.setText( "" );
                }
                else
                {
                    Toast.makeText(MessageActivityOwner.this, "You cannot send empty message.", Toast.LENGTH_SHORT).show();
                }
            }
        } );



        databaseReference.addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customers customer = dataSnapshot.getValue(Customers.class);
                customerName.setText(customer.getFirstName() + " " + customer.getSurname() );

                readMessage( firebaseUser.getUid(), userid );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    // Method for send message
    public void sendMessage(String senderID, String receiverID, String message)
    {
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

                    messageAdapter = new MessageAdapter(getApplicationContext(), chatList);
                    recyclerView.setAdapter( messageAdapter );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

}

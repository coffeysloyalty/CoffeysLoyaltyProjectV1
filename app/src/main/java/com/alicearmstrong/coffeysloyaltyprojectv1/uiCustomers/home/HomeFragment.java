package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.home;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment
{

    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private ImageView qrCodeImage;
    private String userID;
    private TextView txtWelcomeBack;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate( R.layout.fragment_home_customer, container, false);
        qrCodeImage = root.findViewById(R.id.ivQrCode);
        txtWelcomeBack = root.findViewById(R.id.txtWelcomeBack);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
                usersInfo();
            }
        });



        return root;
    }

    public void usersInfo()
    {
        // Method to display user's details from db
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String qrcode = dataSnapshot.child("qrCode").getValue().toString();
                Picasso.with(getActivity()).load(qrcode).into(qrCodeImage);


                // Retrieve users firstname and loyalty points score
                String firstName = dataSnapshot.child("firstName").getValue().toString();
                String loyaltyPoints = dataSnapshot.child("loyaltyScore").getValue().toString();

                //Convert loyalty points string to int
                int points = Integer.parseInt(loyaltyPoints);
                int pointsTillReward = 10 - points;

                txtWelcomeBack.setText("Hello, " + firstName +"." + "\n \n" + "Welcome back to Coffey's Loyalty! Your current loyalty score is : " + points + " \n \n"
                        + "You are currently " + pointsTillReward + " stamps away from collecting your reward.");

                if (points == 10)
                {
                    AlertDialog.Builder ADVoucher = new AlertDialog.Builder(getContext());
                    ADVoucher.setMessage("You have successfully gained 10 points. You can see your reward under Loyalty Card Section");
                    ADVoucher.setCancelable(true);

                    ADVoucher.setPositiveButton("Dismiss",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    dialog.cancel();

                                }
                            });

                    AlertDialog voucher = ADVoucher.create();
                    voucher.show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
    }
}
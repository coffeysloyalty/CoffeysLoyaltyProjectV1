package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.homeOwner;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeOwnerFragment extends Fragment
{

    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private TextView txtWelcomeBack, txtCountdown;
    private Handler handler;
    private Runnable runnable;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_home_owner, container, false);
        txtWelcomeBack = root.findViewById(R.id.txtWelcomeBack);
        txtCountdown = root.findViewById( R.id.txtCountdown );
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        // Set welcome message
        txtWelcomeBack.setText("Hello, Philip \n \nWelcome back to Coffey's Loyalty! \n \nHow many days till Christmas?");
        xmasCountdown();

        return root;
    }

    // Countdown in days for xmas counter
    public void xmasCountdown()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try
                {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date currentDate = new Date();
                    Calendar calendar = Calendar.getInstance();
                    //Get the current year and convert to string
                    int year = calendar.get(Calendar.YEAR);
                    // Set date for mas
                    String xmas = year + "/12/25";

                    Date futureDate = dateFormat.parse(xmas);
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        // Set string countdown
                        String countdown = ("X \n" + "MAS \n" + days + " Days \n" + hours + " Hours \n" + minutes + " Minutes \n" + seconds + " Seconds" );
                        SpannableString spannableString = new SpannableString(countdown);
                        txtCountdown.setText(spannableString);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }


}
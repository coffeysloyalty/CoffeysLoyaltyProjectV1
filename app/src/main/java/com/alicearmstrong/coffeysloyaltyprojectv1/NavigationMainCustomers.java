package com.alicearmstrong.coffeysloyaltyprojectv1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavigationMainCustomers extends AppCompatActivity
{

    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    String userID;
    TextView txtuserEmail, txtUsersName;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation_main_customers );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        txtuserEmail = headerView.findViewById(R.id.txtUserEmail);
        txtUsersName = headerView.findViewById(R.id.txtUsersName);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_loyalty_card, R.id.nav_voucher,
                R.id.nav_chat, R.id.nav_our_products, R.id.nav_about_us,
                R.id.nav_location, R.id.nav_faq)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_main_customers, menu);

         databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                // Retrieve users email and name
                String email = dataSnapshot.child("email").getValue().toString();
                String firstName = dataSnapshot.child("firstName").getValue().toString();
                String surname = dataSnapshot.child("surname").getValue().toString();

                //Set header textviews to current user data
                txtuserEmail.setText(email);
                txtUsersName.setText(firstName + " " + surname);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
                || super.onSupportNavigateUp();
    }
}

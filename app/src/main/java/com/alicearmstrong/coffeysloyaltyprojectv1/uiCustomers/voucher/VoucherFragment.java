package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.voucher;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoucherFragment extends Fragment implements View.OnClickListener {

    private VoucherViewModel voucherViewModel;
    Button btVoucher1, btVoucher2, btVoucher3, btVoucher4, btVoucher5, btVoucher6, btVoucher7, btVoucher8, btVoucher9, btVoucher10;
    TextView txtVoucher;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voucherViewModel = ViewModelProviders.of(this).get(VoucherViewModel.class);
        View root = inflater.inflate( R.layout.fragment_voucher_customer, container, false);
        btVoucher1 = root.findViewById(R.id.btVoucher1);
        btVoucher2 = root.findViewById(R.id.btVoucher2);
        btVoucher3 = root.findViewById(R.id.btVoucher3);
        btVoucher4 = root.findViewById(R.id.btVoucher4);
        btVoucher5 = root.findViewById(R.id.btVoucher5);
        btVoucher6 = root.findViewById(R.id.btVoucher6);
        btVoucher7 = root.findViewById(R.id.btVoucher7);
        btVoucher8 = root.findViewById(R.id.btVoucher8);
        btVoucher9 = root.findViewById(R.id.btVoucher9);
        btVoucher10 = root.findViewById(R.id.btVoucher10);

        btVoucher1.setOnClickListener(this);
        btVoucher2.setOnClickListener(this);
        btVoucher3.setOnClickListener(this);
        btVoucher4.setOnClickListener(this);
        btVoucher5.setOnClickListener(this);
        btVoucher6.setOnClickListener(this);
        btVoucher7.setOnClickListener(this);
        btVoucher8.setOnClickListener(this);
        btVoucher9.setOnClickListener(this);
        btVoucher10.setOnClickListener(this);

        txtVoucher = root.findViewById(R.id.txtVoucher);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        voucherViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                String voucherString = getString(R.string.voucher);
                txtVoucher.setText(voucherString);
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //Check if voucher exists
                boolean voucherExists = dataSnapshot.child("Voucher").exists();
                if (voucherExists == true) {
                    long voucherNumber = dataSnapshot.child("Voucher").getChildrenCount();
                    String voucherCount = String.valueOf(voucherNumber);

                    switch (voucherCount) {
                        case "1":
                            btVoucher1.setVisibility(View.VISIBLE);
                            break;

                        case "2":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            break;

                        case "3":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            break;

                        case "4":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            break;

                        case "5":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            break;


                        case "6":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            btVoucher6.setVisibility(View.VISIBLE);
                            break;


                        case "7":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            btVoucher6.setVisibility(View.VISIBLE);
                            btVoucher7.setVisibility(View.VISIBLE);
                            break;

                        case "8":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            btVoucher6.setVisibility(View.VISIBLE);
                            btVoucher7.setVisibility(View.VISIBLE);
                            btVoucher8.setVisibility(View.VISIBLE);
                            break;

                        case "9":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            btVoucher6.setVisibility(View.VISIBLE);
                            btVoucher7.setVisibility(View.VISIBLE);
                            btVoucher8.setVisibility(View.VISIBLE);
                            btVoucher9.setVisibility(View.VISIBLE);
                            break;


                        case "10":
                            btVoucher1.setVisibility(View.VISIBLE);
                            btVoucher2.setVisibility(View.VISIBLE);
                            btVoucher3.setVisibility(View.VISIBLE);
                            btVoucher4.setVisibility(View.VISIBLE);
                            btVoucher5.setVisibility(View.VISIBLE);
                            btVoucher6.setVisibility(View.VISIBLE);
                            btVoucher7.setVisibility(View.VISIBLE);
                            btVoucher8.setVisibility(View.VISIBLE);
                            btVoucher9.setVisibility(View.VISIBLE);
                            btVoucher10.setVisibility(View.VISIBLE);
                            break;
                    }

                } else {
                    txtVoucher.setText("You currently have no vouchers. Please collect 10 stamps to obtain a voucher!");
                }

                //Get QRCode
               final String qrcode = dataSnapshot.child("qrCode").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btVoucher1:


                break;

        }
    }
}
package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.voucher;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.RegisterScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class VoucherFragment extends Fragment implements View.OnClickListener {

    private VoucherViewModel voucherViewModel;
    Button btVoucher1, btVoucher2, btVoucher3, btVoucher4, btVoucher5, btVoucher6, btVoucher7, btVoucher8, btVoucher9, btVoucher10;
    TextView txtVoucher;
    ImageView voucherImage;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String userID;
    Dialog voucherDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voucherViewModel = ViewModelProviders.of( this ).get( VoucherViewModel.class );
        View root = inflater.inflate( R.layout.fragment_voucher_customer, container, false );
        btVoucher1 = root.findViewById( R.id.btVoucher1 );
        btVoucher2 = root.findViewById( R.id.btVoucher2 );
        btVoucher3 = root.findViewById( R.id.btVoucher3 );
        btVoucher4 = root.findViewById( R.id.btVoucher4 );
        btVoucher5 = root.findViewById( R.id.btVoucher5 );
        btVoucher6 = root.findViewById( R.id.btVoucher6 );
        btVoucher7 = root.findViewById( R.id.btVoucher7 );
        btVoucher8 = root.findViewById( R.id.btVoucher8 );
        btVoucher9 = root.findViewById( R.id.btVoucher9 );
        btVoucher10 = root.findViewById( R.id.btVoucher10 );

        btVoucher1.setOnClickListener( this );
        btVoucher2.setOnClickListener( this );
        btVoucher3.setOnClickListener( this );
        btVoucher4.setOnClickListener( this );
        btVoucher5.setOnClickListener( this );
        btVoucher6.setOnClickListener( this );
        btVoucher7.setOnClickListener( this );
        btVoucher8.setOnClickListener( this );
        btVoucher9.setOnClickListener( this );
        btVoucher10.setOnClickListener( this );

        txtVoucher = root.findViewById( R.id.txtVoucher );

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Customers" ).child( userID );

        voucherViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        } );


        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Check if voucher exists
                boolean voucherExists = dataSnapshot.child( "Voucher" ).exists();
                if (voucherExists == true) {
                    String voucherString = getString( R.string.voucher );
                    txtVoucher.setText( voucherString );

                    long voucherNumber = dataSnapshot.child( "Voucher" ).getChildrenCount();
                    String voucherCount = String.valueOf( voucherNumber );

                    switch (voucherCount) {
                        case "1":
                            btVoucher1.setVisibility( View.VISIBLE );
                            break;

                        case "2":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            break;

                        case "3":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            break;

                        case "4":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            break;

                        case "5":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            break;


                        case "6":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            btVoucher6.setVisibility( View.VISIBLE );
                            break;


                        case "7":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            btVoucher6.setVisibility( View.VISIBLE );
                            btVoucher7.setVisibility( View.VISIBLE );
                            break;

                        case "8":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            btVoucher6.setVisibility( View.VISIBLE );
                            btVoucher7.setVisibility( View.VISIBLE );
                            btVoucher8.setVisibility( View.VISIBLE );
                            break;

                        case "9":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            btVoucher6.setVisibility( View.VISIBLE );
                            btVoucher7.setVisibility( View.VISIBLE );
                            btVoucher8.setVisibility( View.VISIBLE );
                            btVoucher9.setVisibility( View.VISIBLE );
                            break;


                        case "10":
                            btVoucher1.setVisibility( View.VISIBLE );
                            btVoucher2.setVisibility( View.VISIBLE );
                            btVoucher3.setVisibility( View.VISIBLE );
                            btVoucher4.setVisibility( View.VISIBLE );
                            btVoucher5.setVisibility( View.VISIBLE );
                            btVoucher6.setVisibility( View.VISIBLE );
                            btVoucher7.setVisibility( View.VISIBLE );
                            btVoucher8.setVisibility( View.VISIBLE );
                            btVoucher9.setVisibility( View.VISIBLE );
                            btVoucher10.setVisibility( View.VISIBLE );
                            break;
                    }

                } else {
                    txtVoucher.setText( "You currently have no vouchers. Please collect 10 stamps to obtain a voucher!" );
                }

                //Get QRCode
                final String qrcode = dataSnapshot.child( "qrCode" ).getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( "The read failed: " + databaseError.getCode() );
            }
        } );


        return root;

    }

    @Override
    public void onClick(final View v)
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
        LayoutInflater factory = LayoutInflater.from(v.getContext());
        final View view = factory.inflate(R.layout.voucher_dialog, null);
        alertDialog.setView(view);
        voucherImage = view.findViewById(R.id.dialog_imageview);
        alertDialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        databaseReference.addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                switch (v.getId())
                {
                    case R.id.btVoucher1:
                        String voucher1 = dataSnapshot.child("Voucher").child("1").getValue().toString();

                        alertDialog.setTitle( "Voucher 1" );
                        Picasso.with(getActivity()).load(voucher1).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher2:
                        String voucher2 = dataSnapshot.child("Voucher").child("2").getValue().toString();

                        alertDialog.setTitle( "Voucher 2" );
                        Picasso.with(getActivity()).load(voucher2).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher3:
                        String voucher3 = dataSnapshot.child("Voucher").child("3").getValue().toString();

                        alertDialog.setTitle( "Voucher 3" );
                        Picasso.with(getActivity()).load(voucher3).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher4:
                        String voucher4 = dataSnapshot.child("Voucher").child("4").getValue().toString();

                        alertDialog.setTitle( "Voucher 4" );
                        Picasso.with(getActivity()).load(voucher4).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher5:
                        String voucher5 = dataSnapshot.child("Voucher").child("5").getValue().toString();

                        alertDialog.setTitle( "Voucher 5" );
                        Picasso.with(getActivity()).load(voucher5).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher6:
                        String voucher6 = dataSnapshot.child("Voucher").child("6").getValue().toString();

                        alertDialog.setTitle( "Voucher 6" );
                        Picasso.with(getActivity()).load(voucher6).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher7:
                        String voucher7 = dataSnapshot.child("Voucher").child("7").getValue().toString();

                        alertDialog.setTitle( "Voucher 7" );
                        Picasso.with(getActivity()).load(voucher7).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher8:
                        String voucher8 = dataSnapshot.child("Voucher").child("8").getValue().toString();

                        alertDialog.setTitle( "Voucher 8" );
                        Picasso.with(getActivity()).load(voucher8).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher9:
                        String voucher9 = dataSnapshot.child("Voucher").child("9").getValue().toString();

                        alertDialog.setTitle( "Voucher 9" );
                        Picasso.with(getActivity()).load(voucher9).into(voucherImage);
                        alertDialog.show();
                        break;

                    case R.id.btVoucher10:
                        String voucher10 = dataSnapshot.child("Voucher").child("10").getValue().toString();

                        alertDialog.setTitle( "Voucher 10" );
                        Picasso.with(getActivity()).load(voucher10).into(voucherImage);
                        alertDialog.show();
                        break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
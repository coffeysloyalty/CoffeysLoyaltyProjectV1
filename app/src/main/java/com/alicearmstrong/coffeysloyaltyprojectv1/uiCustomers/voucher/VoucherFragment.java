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

    Button btVoucher1, btVoucher2, btVoucher3, btVoucher4, btVoucher5, btVoucher6, btVoucher7, btVoucher8, btVoucher9, btVoucher10;
    TextView txtVoucher;
    ImageView voucherImage;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String userID;
    Dialog voucherDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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


        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                // Check if voucher exists
                boolean voucherExists = dataSnapshot.child( "Voucher" ).exists();
                if (voucherExists == true)
                {
                    String voucherString = getString( R.string.voucher );
                    txtVoucher.setText( voucherString );

                    long voucherNumber = dataSnapshot.child( "Voucher" ).getChildrenCount();
                    String voucherCount = String.valueOf( voucherNumber );

                    // Set voucher button visible depending on number of vouchers in db
                    switch (voucherCount) 
                    {
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

                }
                else
                    {
                    txtVoucher.setText( "You currently have no vouchers. Please collect 10 stamps to obtain a voucher!" );
                    vouchersInvisible();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( "The read failed: " + databaseError.getCode() );
            }
        } );


        return root;

    }



    // OnClick for voucher button
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
                    // Retrieve voucher information depending on which voucher is selected
                    case R.id.btVoucher1:
                        boolean voucherExist1 = dataSnapshot.child( "Voucher" ).child( "1" ).exists();

                        if(voucherExist1==true)
                        {
                            String voucher1 = dataSnapshot.child("Voucher").child("1").getValue().toString();

                            alertDialog.setTitle( "Voucher 1" );
                            Picasso.with(getActivity()).load(voucher1).into(voucherImage);
                            alertDialog.show();

                            break;
                        }

                        if (voucherExist1 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher2:

                        boolean voucherExist2 = dataSnapshot.child( "Voucher" ).child( "2" ).exists();

                        if(voucherExist2==true)
                        {
                            String voucher2 = dataSnapshot.child("Voucher").child("2").getValue().toString();

                            alertDialog.setTitle( "Voucher 2" );
                            Picasso.with(getActivity()).load(voucher2).into(voucherImage);
                            alertDialog.show();

                            if (voucherExist2 == false)
                            {
                                AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                                alertDialognew.setTitle( "Successfully Scanned" );
                                alertDialognew.show();

                                alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.dismiss();

                                    }
                                });


                                break;
                            }

                            break;
                        }

                        break;

                    case R.id.btVoucher3:
                        boolean voucherExist3 = dataSnapshot.child( "Voucher" ).child( "3" ).exists();

                        if(voucherExist3==true )
                        {
                            String voucher3 = dataSnapshot.child("Voucher").child("3").getValue().toString();

                            alertDialog.setTitle( "Voucher 3" );
                            Picasso.with(getActivity()).load(voucher3).into(voucherImage);
                            alertDialog.show();

                            break;
                        }

                        if (voucherExist3 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher4:
                        boolean voucherExist4 = dataSnapshot.child( "Voucher" ).child( "4" ).exists();

                        if(voucherExist4==true)
                        {
                            String voucher4 = dataSnapshot.child("Voucher").child("4").getValue().toString();

                            alertDialog.setTitle( "Voucher 4" );
                            Picasso.with(getActivity()).load(voucher4).into(voucherImage);
                            alertDialog.show();

                            break;
                        }
                        if (voucherExist4 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher5:
                        boolean voucherExist5 = dataSnapshot.child( "Voucher" ).child( "5" ).exists();

                        if(voucherExist5==true)
                        {
                            String voucher5 = dataSnapshot.child("Voucher").child("5").getValue().toString();

                            alertDialog.setTitle( "Voucher 5" );
                            Picasso.with(getActivity()).load(voucher5).into(voucherImage);
                            alertDialog.show();

                            break;
                        }

                        if (voucherExist5 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }


                        break;

                    case R.id.btVoucher6:
                        boolean voucherExist6= dataSnapshot.child( "Voucher" ).child( "6" ).exists();

                        if(voucherExist6==true)
                        {
                            String voucher6 = dataSnapshot.child("Voucher").child("6").getValue().toString();

                            alertDialog.setTitle( "Voucher 6" );
                            Picasso.with(getActivity()).load(voucher6).into(voucherImage);
                            alertDialog.show();

                            break;
                        }
                        if (voucherExist6 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher7:
                        boolean voucherExist7 = dataSnapshot.child( "Voucher" ).child( "7" ).exists();

                        if(voucherExist7==true)
                        {
                            String voucher7 = dataSnapshot.child("Voucher").child("7").getValue().toString();

                            alertDialog.setTitle( "Voucher 7" );
                            Picasso.with(getActivity()).load(voucher7).into(voucherImage);
                            alertDialog.show();

                            break;
                        }
                        if (voucherExist7 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher8:
                        boolean voucherExist8 = dataSnapshot.child( "Voucher" ).child( "8" ).exists();

                        if(voucherExist8==true)
                        {
                            String voucher8 = dataSnapshot.child("Voucher").child("8").getValue().toString();

                            alertDialog.setTitle( "Voucher 8" );
                            Picasso.with(getActivity()).load(voucher8).into(voucherImage);
                            alertDialog.show();

                            break;
                        }
                        if (voucherExist8 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });

                            break;
                        }

                        break;

                    case R.id.btVoucher9:
                        boolean voucherExist9 = dataSnapshot.child( "Voucher" ).child( "9" ).exists();

                        if(voucherExist9==true)
                        {
                            String voucher9 = dataSnapshot.child("Voucher").child("9").getValue().toString();

                            alertDialog.setTitle( "Voucher 9" );
                            Picasso.with(getActivity()).load(voucher9).into(voucherImage);
                            alertDialog.show();

                            break;
                        }

                        if (voucherExist9 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });
                            break;
                        }

                        break;

                    case R.id.btVoucher10:
                        boolean voucherExist10 = dataSnapshot.child( "Voucher" ).child( "10" ).exists();

                        if(voucherExist10==true)
                        {
                            String voucher10 = dataSnapshot.child("Voucher").child("10").getValue().toString();

                            alertDialog.setTitle( "Voucher 10" );
                            Picasso.with(getActivity()).load(voucher10).into(voucherImage);
                            alertDialog.show();
                            break;
                        }
                        if (voucherExist10 == false)
                        {
                            AlertDialog.Builder alertDialognew = new AlertDialog.Builder(v.getContext());
                            alertDialognew.setTitle( "Successfully Scanned" );
                            alertDialognew.show();

                            alertDialognew.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            });
                            break;
                        }

                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    public void vouchersInvisible()

    {
        btVoucher1.setVisibility( View.GONE );
        btVoucher2.setVisibility( View.GONE );
        btVoucher3.setVisibility( View.GONE );
        btVoucher4.setVisibility( View.GONE );
        btVoucher5.setVisibility( View.GONE );
        btVoucher6.setVisibility( View.GONE );
        btVoucher7.setVisibility( View.GONE );
        btVoucher8.setVisibility( View.GONE );
        btVoucher9.setVisibility( View.GONE );
        btVoucher10.setVisibility(View.GONE );
    }
}
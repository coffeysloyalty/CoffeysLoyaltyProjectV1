
package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.barcodeScanerOwner;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.RegisterScreen;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarcodeScannerOwnerFragment extends Fragment
{

    SurfaceView surfaceView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    TextView textView;

    DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_barcode_scanner_owner, container, false);

        surfaceView = root.findViewById(R.id.sfCameraView);
        textView = root.findViewById(R.id.textView);
        barcodeDetector = new BarcodeDetector.Builder(getActivity()).setBarcodeFormats( Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(getActivity(),barcodeDetector).setRequestedPreviewSize(640,480).build();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Customers");

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                // Check camera permissions
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }

                // Start camera
                try
                {
                    cameraSource.start(holder);
                    surfaceView.setFocusableInTouchMode(true);
                    surfaceView.setFocusable(true);
                    surfaceView.requestFocus();
                }
                catch (IOException e )
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
                try {
                    cameraSource.start(holder);
                    surfaceView.setFocusableInTouchMode(true);
                    surfaceView.setFocusable(true);
                    surfaceView.requestFocus();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>()
        {
            @Override
            public void release()
            {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections)
            {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if (qrCodes.size()!=0)
                {
                    textView.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            //Enable vibrate when scanned
                            Vibrator vibrator = (Vibrator) getContext().getSystemService( Context.VIBRATOR_SERVICE );
                            vibrator.vibrate( 1000 );
                            cameraSource.stop();

                            final String qrcode = qrCodes.valueAt( 0 ).displayValue;

                            // if qr code value is the users email and voucher code
                            if (qrcode.matches( "^(.+)@(.+)/(.+)/(.+)$" ))
                            {
                                String qrEmail1 = null;
                                String qrEmail2 = null;
                                String qrEmail = null;
                                String qrVoucherNumber = null;
                                final Pattern pattern = Pattern.compile( "^(.+)@(.+)/(.+)/(.+)$" );
                                final Matcher matcher = pattern.matcher( qrcode );

                                if (matcher.matches())
                                {
                                    qrEmail1 = matcher.group(1);
                                    qrEmail2 = matcher.group(2);
                                    qrEmail = qrEmail1 + "@" + qrEmail2;
                                    qrVoucherNumber = matcher.group(4);
                                }

                                final String finalQrVoucherNumber = qrVoucherNumber;

                                // In db look for users information whos email address is equal to email address from QR code
                                databaseReference.orderByChild( "email" ).equalTo(qrEmail).addChildEventListener( new ChildEventListener()
                                {


                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                                    {
                                        String uuid = dataSnapshot.getKey();

                                        databaseReference.child( uuid ).child( "Voucher" ).child( finalQrVoucherNumber ).setValue( null );
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                textView.setText( "Voucher Number:" + qrVoucherNumber);
                            }

                           // if email from QR code matches the regex method
                          else  if(qrcode.matches( "^(.+)@(.+)$"))
                            {
                                String qrEmail = null;
                                final Pattern pattern = Pattern.compile( "^(.+)@(.+)$" );
                                final Matcher matcher = pattern.matcher( qrcode );


                                if (matcher.matches())
                                {
                                    qrEmail = matcher.group(0);
                                }

                                // Update database with new loyalty score
                                databaseReference.orderByChild( "email" ).equalTo(qrEmail).addChildEventListener( new ChildEventListener()
                                {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    // Get users info from db
                                    final String firstName = dataSnapshot.child( "firstName" ).getValue().toString();
                                    final String surname = dataSnapshot.child( "surname" ).getValue().toString();
                                    final String email = dataSnapshot.child( "email" ).getValue().toString();
                                    final String loyaltyPoints = dataSnapshot.child( "loyaltyScore" ).getValue().toString();

                                    boolean historyExists = dataSnapshot.child( "History" ).exists();
                                    String uuid = dataSnapshot.getKey();
                                    int points = Integer.parseInt( loyaltyPoints );

                                    // Set textview to users data
                                    textView.setText( "Name: " + firstName + " " + surname + "\n" + "Email: " + email );

                                    // Get date & time when QR Code is scanned
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy hh:mm:ss" );
                                    String dateStamp = simpleDateFormat.format( new Date() );

                                    // If points = 1 & customer has stamp history, old history is deleted and new history started
                                    if (points == 0 && historyExists == true)
                                    {
                                        int count;

                                        for (count = 1; count <= 10; count++) {
                                            final String stampDate = dataSnapshot.child( "History" ).child( "1" ).getValue().toString();

                                            databaseReference.child( uuid ).child( "History" ).child( String.valueOf( count ) ).setValue( null );
                                            databaseReference.child( uuid ).child( "History" ).child( "1" ).setValue( stampDate );
                                        }

                                    }
                                    // Increase points if less than 10
                                    if (points < 10) {
                                        points++;
                                        databaseReference.child( uuid ).child( "loyaltyScore" ).setValue( points );
                                        databaseReference.child( uuid ).child( "History" ).child( String.valueOf( points ) ).setValue( dateStamp );

                                        // If score is at 10, restart counter and reward user with voucher
                                        if (points == 10) {
                                            try {
                                                Thread.sleep( 3000 );
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            databaseReference.child( uuid ).child( "loyaltyScore" ).setValue( "0" );

                                            int voucherCount = 1;
                                            boolean voucherExists = dataSnapshot.child( "Voucher" ).exists();

                                            // if voucher does exist, increase the total number of vouchers by 1, if not create new voucher
                                            if (voucherExists == true)
                                            {
                                                long voucherNumber = dataSnapshot.child( "Voucher" ).getChildrenCount();
                                                voucherNumber++;

                                                databaseReference.child( uuid ).child( "Voucher" ).child( String.valueOf( voucherNumber ) ).setValue( "https://api.qrserver.com/v1/create-qr-code/?size=500x500&data=" + email + "/Voucher/" + voucherNumber );
                                            }
                                            else
                                                {
                                                databaseReference.child( uuid ).child( "Voucher" ).child( String.valueOf( voucherCount ) ).setValue( "https://api.qrserver.com/v1/create-qr-code/?size=500x500&data=" + email + "/Voucher/" + voucherCount );

                                            }


                                        }
                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    System.out.println( "The read failed: " + databaseError.getCode() );
                                }

                            } );
                        }

                            // Create alert dialog for successfully scanned QR Code
                            AlertDialog.Builder ADSuccessfulScan = new AlertDialog.Builder(getContext());
                            ADSuccessfulScan.setMessage("The QR Code has successfully been scanned. The stamp has been successfully added.");
                            ADSuccessfulScan.setCancelable(true);

                            ADSuccessfulScan.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int id)
                                        {
                                            dialog.cancel();

                                        }
                                    });

                            // Display alert diaglog box
                            ADSuccessfulScan.create();
                            ADSuccessfulScan.show();

                        }
                    });

                }
            }
        });



        return root;

    }


}
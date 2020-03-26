package com.alicearmstrong.coffeysloyaltyprojectv1;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.database.Customers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity
{
     Button btRegister, btCancel, btDatePicker;
     TextView tvDisplayDate;
     EditText etFirstName, etSurname, etContactNumber, etEmail, etPassword, etPasswordRepeat;
     ProgressBar pbRegister;
     FirebaseAuth firebaseAuth;
     FirebaseUser firebaseUser;
     DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        btCancel = findViewById(R.id.btCancel);
        btRegister = findViewById(R.id.btRegister);
        btDatePicker = findViewById(R.id.btDOB);
        tvDisplayDate = findViewById(R.id.etDisplayDate);
        etFirstName = findViewById(R.id.etFirstName);
        etSurname = findViewById(R.id.etSurname);
        etContactNumber = findViewById(R.id.etContactNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordRepeat = findViewById(R.id.etPasswordRepeat);
        pbRegister = findViewById(R.id.pbRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference= database.getReference("Customers");
    }

    // Method to display date picker
    public void onClickDate(View view)
    {
        btDatePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "Date Picker");
            }
        });
    }


    // Method for cancel button
    public void onClickCancel(View view)
    {
        btCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent_cancel = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent_cancel);
                finish();
            }
        });
    }

    // Method for register button
    public void onClickRegister(View view)
    {
        btRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RegisterUser();
            }
        });
    }

    // Method to register user
    public void RegisterUser()
    {
        String firstName = etFirstName.getText().toString();
        String surname = etSurname.getText().toString();
        String dob = tvDisplayDate.getText().toString();
        String contactNumber = etContactNumber.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordRepeat = etPasswordRepeat.getText().toString();

        // Validation
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(RegisterScreen.this, "Please enter your First Name", Toast.LENGTH_LONG).show();
            etFirstName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(surname)) {
            Toast.makeText(RegisterScreen.this, "Please enter your Surname", Toast.LENGTH_LONG).show();
            etSurname.requestFocus();
            return;
        } else if (TextUtils.isEmpty(dob)) {
            Toast.makeText(RegisterScreen.this, "Please select your Date Of Birth.", Toast.LENGTH_LONG).show();
            btDatePicker.requestFocus();
        } else if (TextUtils.isEmpty(contactNumber)) {
            Toast.makeText(RegisterScreen.this, "Please enter your Contact Number. ", Toast.LENGTH_LONG).show();
            etContactNumber.requestFocus();
            return;
        } else if (contactNumber.length() < 11) {
            etContactNumber.setError("Invalid phone number, please re-enter your Contact Number.");
            etContactNumber.setText("");
            etContactNumber.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(RegisterScreen.this, "Please enter an Email Address", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterScreen.this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        } else if (password.length() < 6) {
            etPassword.setError("Password too short, enter minimum 6 characters.");
        } else if (TextUtils.isEmpty(passwordRepeat)) {
            Toast.makeText(RegisterScreen.this, "Please confirm password", Toast.LENGTH_LONG).show();
            return;
        } else if (passwordRepeat.length() < 6) {
            etPasswordRepeat.setError("Password too short, enter minimum 6 characters.");
        } else if (!password.equals(passwordRepeat)) {
            etPasswordRepeat.setError("Password's do not match. Please re-enter password");
            etPasswordRepeat.setText("");
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email, passwordRepeat)
                    .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                btRegister.setVisibility(View.GONE);
                                pbRegister.setVisibility(View.VISIBLE);

                                firebaseUser = firebaseAuth.getCurrentUser();

                                //Send email verification to new user
                                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("RegisterScreen", "Successfully Registered.");

                                                    // Create alert dialog for successful registration
                                                    AlertDialog.Builder ADRegisterSuccess = new AlertDialog.Builder(RegisterScreen.this);
                                                    ADRegisterSuccess.setMessage("Account has been successfully registered. An authentication" +
                                                            "email has been sent. Please confirm your email address before logging in");
                                                    ADRegisterSuccess.setCancelable(true);

                                                    ADRegisterSuccess.setPositiveButton("Dismiss",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id)
                                                                {
                                                                    dialog.cancel();
                                                                }
                                                            });

                                                    ADRegisterSuccess.create();
                                                    ADRegisterSuccess.show();

                                                    pbRegister.setVisibility(View.GONE);
                                                    btRegister.setVisibility(View.VISIBLE);
                                                    insertData();
                                                    firebaseAuth.signOut();
                                                    clearForm();






                                                }
                                            }
                                        });
                            }
                            //Method for unsuccessful registration
                            else if (!task.isSuccessful()) {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Log.d("RegisterScreen", errorCode);

                                switch (errorCode)
                                {
                                    case "ERROR_EMAIL_ALREADY_IN_USE":

                                        // Create alert dialog for email already exists
                                        AlertDialog.Builder ADEmailUsed = new AlertDialog.Builder(RegisterScreen.this);
                                        ADEmailUsed.setMessage("This Email has already been registered. Please login or reset your password.");
                                        ADEmailUsed.setCancelable(true);


                                        ADEmailUsed.setPositiveButton("Dismiss",
                                                new DialogInterface.OnClickListener()
                                                {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        ADEmailUsed.create();
                                        ADEmailUsed.show();
                                        etEmail.requestFocus();
                                        etEmail.setText("");
                                        break;

                                    case "ERROR_INVALID_EMAIL":

                                        // Create alert dialog for invalid email
                                        AlertDialog.Builder ADInvalidEmail = new AlertDialog.Builder(RegisterScreen.this);
                                        ADInvalidEmail.setMessage("The email address entered is not valid. Please re-enter your email address");
                                        ADInvalidEmail.setCancelable(true);

                                        ADInvalidEmail.setPositiveButton("Dismiss",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        ADInvalidEmail.create();
                                        ADInvalidEmail.show();
                                        break;


                                }

                            }
                        }


                    });


        }
    }
        // Insert data into db
        public void insertData()
        {
            String firstName = etFirstName.getText().toString();
            String surname = etSurname.getText().toString();
            String dob = tvDisplayDate.getText().toString();
            String contactNumber = etContactNumber.getText().toString();
            String email = etEmail.getText().toString();
            String qrCode = ("https://api.qrserver.com/v1/create-qr-code/?size=500x500&data=" + email);
            int loyaltyScore = 0;

            // Set db id equal to userID
            String id = firebaseUser.getUid();

            Customers CustomerDetails = new Customers(firstName, surname,dob,contactNumber,email, qrCode, loyaltyScore);
            databaseReference.child(id).setValue(CustomerDetails);


        }

        //Clear data entered
        public void clearForm()
        {
            etFirstName.setText("");
            etSurname.setText("");
            tvDisplayDate.setText("");
            etContactNumber.setText("");
            etEmail.setText("");
            etPassword.setText("");
            etPasswordRepeat.setText("");
        }
}

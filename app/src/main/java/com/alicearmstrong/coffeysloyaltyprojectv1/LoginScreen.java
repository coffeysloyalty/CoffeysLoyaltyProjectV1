package com.alicearmstrong.coffeysloyaltyprojectv1;

import android.app.AlertDialog;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity
{
     EditText etEmail, etPassword;
     Button btLogin, btRegister, btForgotPassword;
     ProgressBar pbLogin;
     FirebaseAuth firebaseAuth;
     FirebaseUser firebaseUser;
     String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        etEmail = findViewById(R.id.etEmailAddresss);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        btForgotPassword = findViewById(R.id.btForgotPassword);
        pbLogin = findViewById(R.id.pbLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userID = firebaseAuth.getUid();

        // Method to check if there is a user currently logged on
        if (firebaseAuth.getCurrentUser() != null)
        {
            checkIfAdmin();
        }
    }

    //Method for login button
    public void onClickLogin(View view)
    {
        btLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
    }

    public void LoginUser()
    {
        //Input to string
        final String Email = etEmail.getText().toString();
        final String Password = etPassword.getText().toString();

        //Validation
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(getApplicationContext(), "Please enter an email address.", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Password))
        {
            Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }
        else if (Password.length() < 6)
        {
            etPassword.setError("Password too short, enter minimum 6 characters.");
            etPassword.requestFocus();

        }
        //Method to login user
        else
            {
            firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        btLogin.setVisibility(View.GONE);
                                        pbLogin.setVisibility(View.VISIBLE);
                                        firebaseUser = firebaseAuth.getCurrentUser();
                                        userID = firebaseAuth.getUid();


                                        // Check if the user has verified their email address
                                        if (firebaseUser.isEmailVerified())
                                        {
                                            checkIfAdmin();

                                            Log.d("RegisterScreen", "Email verified.");

                                        }
                                        //Method if user has not verified their email
                                        else if (!firebaseUser.isEmailVerified())
                                        {
                                            // Create alert dialog for email  address not verified
                                            AlertDialog.Builder ADemailNotVerified = new AlertDialog.Builder(LoginScreen.this);
                                            ADemailNotVerified.setMessage("You have not verified your email address. Please check your email's");
                                            ADemailNotVerified.setCancelable(true);

                                            ADemailNotVerified.setPositiveButton("Dismiss",
                                                    new DialogInterface.OnClickListener()
                                                    {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });

                                            ADemailNotVerified.create();
                                            ADemailNotVerified.show();

                                            btLogin.setVisibility(View.VISIBLE);
                                            pbLogin.setVisibility(View.GONE);

                                            Log.d("LoginScreen", "Email not verified.");
                                        }
                                    }
                                    else if (!task.isSuccessful())
                                    {
                                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                        Log.d("LoginScreen", errorCode);

                                        switch (errorCode)
                                        {
                                            case "ERROR_WRONG_PASSWORD":

                                                // Create alert dialog for incorrect password
                                                AlertDialog.Builder ADWrongPassword = new AlertDialog.Builder(LoginScreen.this);
                                                ADWrongPassword.setMessage("The password supplied is incorrect. Please re-enter your password.");
                                                ADWrongPassword.setCancelable(true);

                                                ADWrongPassword.setPositiveButton("Dismiss",
                                                        new DialogInterface.OnClickListener()
                                                        {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                                ADWrongPassword.create();
                                                ADWrongPassword.show();

                                                etPassword.setError("Password is incorrect ");
                                                etPassword.requestFocus();
                                                etPassword.setText("");
                                                break;

                                            case "ERROR_USER_NOT_FOUND":

                                                // Create alert dialog for user not recognised
                                                AlertDialog.Builder ADUserNotFound = new AlertDialog.Builder(LoginScreen.this);
                                                ADUserNotFound.setMessage("There is no user corresponding to this email address. Please check your email address or register an account.");
                                                ADUserNotFound.setCancelable(true);

                                                ADUserNotFound.setPositiveButton("Dismiss",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                                ADUserNotFound.create();
                                                ADUserNotFound.show();

                                                break;

                                            case "ERROR_INVALID_EMAIL":

                                                // Create alert dialog for invalid email
                                                AlertDialog.Builder ADInvalidEmail = new AlertDialog.Builder(LoginScreen.this);
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

                                                etEmail.setError("Invalid Email ");
                                                etEmail.requestFocus();
                                                break;

                                        }
                                    }

                                }
                            }
                    );
        }
    }

    // OnClick to open register screen
    public void onClickRegister(View view)
    {
        btRegister.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_register = new Intent( LoginScreen.this, RegisterScreen.class );
                startActivity( intent_register );
                finish();
            }
        });
    }

    // OnClick to open forgotten password screen
    public void onClickForgotPassword(View view)
    {
        btForgotPassword.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_forgotPassword = new Intent( LoginScreen.this, ResetPassword.class );
                startActivity( intent_forgotPassword );
                finish();
            }
        });
    }

    public void checkIfAdmin()
    {
        if (userID.equals("lYr5teVjoFP7TkXPowgARdDBzV83") )
        {
            Intent intent_admin = new Intent( LoginScreen.this, NavigationMainOwner.class );
            startActivity( intent_admin );
            finish();
        }
        else
        {
            Intent intent_customer = new Intent(LoginScreen.this, NavigationMainCustomers.class);
            startActivity(intent_customer);
            finish();
        }
    }
}

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity
{

     EditText etEmailReset;
     Button btReturn, btResetPassword;
     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmailReset = findViewById(R.id.etEmailReset);
        btReturn = findViewById(R.id.btReturn);
        btResetPassword = findViewById(R.id.btResetPassword);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Method for return to login screen button
    public void onClickReturn(View view)
    {
        btReturn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_returnLogin = new Intent(ResetPassword.this, LoginScreen.class);
                startActivity(intent_returnLogin);
                finish();
            }
        });

    }

    // Method for reset password button
    public void onClickResetPassword(View view)
    {
        btResetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String Email = etEmailReset.getText().toString().trim();

                if (TextUtils.isEmpty(Email))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Send reset password email method
                firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Need to add in progress bar
                            // Create alert dialog for rest password successful
                            AlertDialog.Builder ADRestPassword = new AlertDialog.Builder(ResetPassword.this);
                            ADRestPassword.setMessage("An email to reset your password has been sent. Please check your emails. .");
                            ADRestPassword.setCancelable(true);


                            ADRestPassword.setPositiveButton("Dismiss",
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int id)
                                        {
                                            dialog.cancel();
                                        }
                                    });

                            ADRestPassword.create();
                            ADRestPassword.show();
                            etEmailReset.setText("");

                            Log.d("ResetPassword", "Reset email has been sent.");
                            return;
                        }

                        else
                        {
                            // Create alert dialog for incorrect email
                            AlertDialog.Builder ADincorrectEmail = new AlertDialog.Builder(ResetPassword.this);
                            ADincorrectEmail.setMessage("The email entered is incorrect. Please check the email address entered.");
                            ADincorrectEmail.setCancelable(true);

                            ADincorrectEmail.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            ADincorrectEmail.create();
                            ADincorrectEmail.show();
                            etEmailReset.requestFocus();
                            etEmailReset.setText("");

                            Log.d("ResetPassword", "Incorrect email entered.");
                            return;
                        }
                    }
                });

            }
        });

    }
}

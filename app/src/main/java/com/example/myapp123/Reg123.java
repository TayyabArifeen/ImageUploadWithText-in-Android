package com.example.myapp123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reg123 extends AppCompatActivity {

    EditText username,password,email;
    Button register;
    private FirebaseAuth mFirebaseAnalytic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg123);
        mFirebaseAnalytic = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.registrationButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate()) {
                    final String u_name = username.getText().toString().trim();
                    String u_pwd = password.getText().toString().trim();
                    String u_em = email.getText().toString().trim();
                    mFirebaseAnalytic.createUserWithEmailAndPassword(u_em, u_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                userVerification();
                            } else {
                                Toast.makeText(Reg123.this, " Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }
            }
        });
    }

            private Boolean Validate() {
                Boolean result = false;
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                String em = email.getText().toString();

                if (name.isEmpty() && pwd.isEmpty() && em.isEmpty()) {
                    Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                } else {
                    result = true;
                }
                return result;
            }

    private void userVerification()
    {
        FirebaseUser firebaseUser=mFirebaseAnalytic.getCurrentUser();

        if(firebaseUser!=null)
        {
         firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful())
                 {
                     Toast.makeText(Reg123.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                     mFirebaseAnalytic.signOut();
                     finish();
                     startActivity(new Intent(Reg123.this,MainActivity.class));
                 }
                 else
                 {
                     Toast.makeText(Reg123.this,"No Verification Done",Toast.LENGTH_SHORT).show();
                 }
             }
         });

        }

    }
        }


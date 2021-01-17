package com.example.myapp123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText user_email,user_password;
    Button btnLogin,btnRegister;
    TextView forgotpwd;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_email=(EditText) findViewById(R.id.editEmail);
        user_password=(EditText) findViewById((R.id.editPassword));
        btnLogin=(Button) findViewById(R.id.buttonLogin);
        btnRegister=(Button) findViewById(R.id.buttonRegister);
        forgotpwd=(TextView) findViewById(R.id.textView3);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
           // finish();
           // startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(user_email.getText().toString(),user_password.getText().toString());
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Reg123.class));
            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetPwd.class));
            }
        });
    }

    private void validate(String username,String userpassword)
    {
        firebaseAuth.signInWithEmailAndPassword(username,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Failed!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void userVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailVerified=firebaseUser.isEmailVerified();

        if(emailVerified)
        {
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }
        else
        {
            Toast.makeText(MainActivity.this,"Verify Email!!!",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
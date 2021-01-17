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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPwd extends AppCompatActivity {

    EditText emailReset;
    Button btnreset;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        emailReset=(EditText) findViewById(R.id.editReset);
        btnreset=(Button) findViewById(R.id.buttonReset);
        firebaseAuth=FirebaseAuth.getInstance();
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail=emailReset.getText().toString().trim();
                if(userEmail.equals(""))
                {
                    Toast.makeText(ResetPwd.this,"Provide Email",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ResetPwd.this,"Reset Link Sent to Email",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ResetPwd.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(ResetPwd.this,"Error sending link check your Email!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
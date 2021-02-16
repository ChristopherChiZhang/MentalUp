package com.example.mentalup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button confirmlogin_button;
    private EditText emailID, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        confirmlogin_button = findViewById(R.id.confirmLogin);
        confirmlogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty() && !pwd.isEmpty()) {
                    emailID.setError("Missing email address.");
                    emailID.requestFocus();
                }
                else if(pwd.isEmpty() && !email.isEmpty()) {
                    password.setError("Missing Password.");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Missing fields are required.", Toast.LENGTH_SHORT).show();
                }
                else if(!email.isEmpty() && !pwd.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                toMenupage();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "An error occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toMenupage() {
        Intent intentToMenuPage = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intentToMenuPage);
    }
}
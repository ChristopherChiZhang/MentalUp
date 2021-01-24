package com.example.mentalup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private Button signup_button;
    private EditText emailID, password, firstName, lastName;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private static final String TAG = "RegisterActivity";
    private User newUser;
    private boolean doesExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);


        signup_button = findViewById(R.id.signupbutton);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString().replaceAll("\\s", "");
                String pwd = password.getText().toString().replaceAll("\\s", "");
                if (email.isEmpty()) {
                    emailID.setError("Email address cannot be blank.");
                    emailID.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Password cannot be blank.");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email address and password are required.", Toast.LENGTH_SHORT).show();
                } else if (!email.isEmpty() && !pwd.isEmpty()) {
                    if (true) { //!userExists(email)
                        newUser = new User(firstName.getText().toString().replaceAll("\\s", ""),
                                lastName.getText().toString().replaceAll("\\s", ""), email.replaceAll("\\s", ""), 0);
                        registerUser(email, pwd);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Email account exists.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "An error occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backToHomepage() {
        Intent intentBackToHomepage = new Intent(RegisterActivity.this, HomepageActivity.class);
        startActivity(intentBackToHomepage);
    }

    public void registerUser(String email, String pwd) {
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error creating account. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser) {
        String keyID = mDatabase.push().getKey();
        mDatabase.child("users").child(keyID).setValue(newUser);
        backToHomepage();
    }
}


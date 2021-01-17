package com.example.mentalup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomepageActivity extends AppCompatActivity {
    public Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        login_button = findViewById(R.id.buttonlogin);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginPage();
            }
        });
    }

    public void toLoginPage() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
    }
}
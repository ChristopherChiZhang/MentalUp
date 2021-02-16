package com.example.mentalup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button activityPage;
    private Button statisticsPage;
    private Button profilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        activityPage = findViewById(R.id.buttonActivities);
        statisticsPage = findViewById(R.id.buttonStats);
        profilePage = findViewById(R.id.buttonProfile);

        activityPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivityPage();
            }
        });

        statisticsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toStatisticsPage();
            }
        });

        profilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfilePage();
            }
        });
    }

    public void toActivityPage() {
        Intent intentToActivityPage = new Intent(MenuActivity.this, ActivitypageActivity.class);
        startActivity(intentToActivityPage);
    }

    public void toStatisticsPage() {
        Intent intentToStatisticsPage = new Intent(MenuActivity.this, StatisticsActivity.class);
        startActivity(intentToStatisticsPage);
    }

    public void toProfilePage() {
        Intent intentToProfilePage = new Intent(MenuActivity.this, ProfileActivity.class);
        startActivity(intentToProfilePage);
    }
}
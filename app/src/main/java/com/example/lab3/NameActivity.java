package com.example.lab3;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        // Get the name passed from MainActivity
        String userName = getIntent().getStringExtra("userName");

        // Find TextView and set the welcome message
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome " + userName + "!");

        // Buttons
        Button thankYouButton = findViewById(R.id.thankYouButton);
        Button dontCallMeButton = findViewById(R.id.dontCallMeButton);

        // Set listeners for the buttons
        thankYouButton.setOnClickListener(v -> {
            // User is happy with the name
            setResult(1);  // Set result code 1
            finish();      // Close NameActivity
        });

        dontCallMeButton.setOnClickListener(v -> {
            // User doesn't like the name
            setResult(0);  // Set result code 0
            finish();      // Close NameActivity
        });
    }
}
package com.example.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCE_KEY = "userName";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText and Button
        nameEditText = findViewById(R.id.nameEditText);
        Button nextButton = findViewById(R.id.nextButton);

        // Load saved name from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString(PREFERENCE_KEY, "");
        if (!savedName.isEmpty()) {
            nameEditText.setText(savedName);
        }

        // Set the button click listener
        nextButton.setOnClickListener(v -> {
            String userName = nameEditText.getText().toString().trim();

            // Start NameActivity and pass the name to it
            Intent intent = new Intent(MainActivity.this, NameActivity.class);
            intent.putExtra("userName", userName);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the current name in SharedPreferences when the activity pauses
        String currentName = nameEditText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCE_KEY, currentName);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == 0) {
                // User wants to change their name (do nothing, return to MainActivity)
                nameEditText.setText("");
            } else if (resultCode == 1) {
                // User is happy with the name, close the app
                finish();
            }
        }
    }
}
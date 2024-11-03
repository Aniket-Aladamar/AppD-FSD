package com.example.placement_btech;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        String company = getIntent().getStringExtra("company");
        String contact = getIntent().getStringExtra("contact");
        boolean notificationsEnabled = getIntent().getBooleanExtra("notificationsEnabled", false);
        String selectedDate = getIntent().getStringExtra("selectedDate");
        String selectedTime = getIntent().getStringExtra("selectedTime");
        float rating = getIntent().getFloatExtra("rating", 0);

        TextView displayText = findViewById(R.id.displayText);
        displayText.setText("Company: " + company +
                "\nContact Person: " + contact +
                "\nNotifications: " + notificationsEnabled +
                "\nDate: " + selectedDate +
                "\nTime: " + selectedTime +
                "\nRating: " + rating);
    }
}

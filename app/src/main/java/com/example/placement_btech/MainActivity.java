package com.example.placement_btech;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewSwitcher;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText companyName, contactPerson;
    private Switch notificationsSwitch;
    private TextView dateTextView, timeTextView;
    private Button selectDateButton, selectTimeButton, submitButton, websiteButton;
    private RatingBar ratingBar;
    private String selectedDate, selectedTime;
    private ImageSwitcher imageSwitcher;
    private int imageIndex = 0;
    private int[] images = {R.drawable.images01, R.drawable.image02, R.drawable.image03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyName = findViewById(R.id.input_company_name);
        contactPerson = findViewById(R.id.input_contact_person);
        notificationsSwitch = findViewById(R.id.switch_notifications);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        selectDateButton = findViewById(R.id.btnSelectDate);
        selectTimeButton = findViewById(R.id.btnSelectTime);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.btnSubmit);
        websiteButton = findViewById(R.id.btnWebsite);
        imageSwitcher = findViewById(R.id.imageSwitcher);

        // Image Switcher
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });
        imageSwitcher.setImageResource(images[imageIndex]);

        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageIndex = (imageIndex + 1) % images.length;
                imageSwitcher.setImageResource(images[imageIndex]);
            }
        });

        // DatePicker Dialog
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateTextView.setText(selectedDate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // TimePicker Dialog
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime = hourOfDay + ":" + minute;
                        timeTextView.setText(selectedTime);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        // Explicit Intent to pass data to NextActivity
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company = companyName.getText().toString();
                String contact = contactPerson.getText().toString();
                boolean notificationsEnabled = notificationsSwitch.isChecked();
                float rating = ratingBar.getRating();

                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra("company", company);
                intent.putExtra("contact", contact);
                intent.putExtra("notificationsEnabled", notificationsEnabled);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedTime", selectedTime);
                intent.putExtra("rating", rating);
                startActivity(intent);
            }
        });

        // Implicit Intent to open company website
        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.company-website.com"));
                startActivity(implicitIntent);
            }
        });
    }
}

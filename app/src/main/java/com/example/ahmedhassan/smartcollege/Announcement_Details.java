package com.example.ahmedhassan.smartcollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Announcement_Details extends AppCompatActivity {
    TextView titleTextView,dateTextView,bodyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement__details);

        titleTextView=(TextView)findViewById(R.id.announcemenTitleDetailTextView);
        dateTextView=(TextView)findViewById(R.id.announcemenDateDetailTextView);
        bodyTextView=(TextView)findViewById(R.id.announcemenBodyDetailTextView);

        String str=getIntent().getStringExtra("announce");
        String values[]=str.split("==");
        titleTextView.setText(values[0]);
        dateTextView.setText(values[1]);
        bodyTextView.setText(values[2]);
    }
}

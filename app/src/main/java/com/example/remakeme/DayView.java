package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DayView extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(date);

        /*add the toolbar and enable upwards navigation*/
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /*public void goToCalendar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
}
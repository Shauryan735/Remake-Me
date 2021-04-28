package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    public String DATE_MESSAGE = "Meme";
    public String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        date = intent.getStringExtra(MainActivity.DATE_MESSAGE);
    }

    public void goToCalendar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void dayView(View view) {
        Intent intent = new Intent(this, com.example.remakeme.DayView.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void newEvent(View view) {
        Intent intent = new Intent(this, com.example.remakeme.AddEvent.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void infographics(View view) {
        Intent intent = new Intent(this, com.example.remakeme.Infographics.class);
        startActivity(intent);
    }

    public void dailyReflection(View view) {
        Intent intent = new Intent(this, com.example.remakeme.DailyReflection.class);
        startActivity(intent);
    }
}
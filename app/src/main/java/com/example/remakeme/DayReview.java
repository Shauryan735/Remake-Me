package com.example.remakeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DayReview extends AppCompatActivity {


  final static String dateMessage = "Meme";
  String date = "Meme 2.0";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_day_review);

    Intent intent = getIntent();
    date = intent.getStringExtra(dateMessage);

    Toast.makeText(this,
        date, Toast.LENGTH_LONG)
        .show();
  }
}
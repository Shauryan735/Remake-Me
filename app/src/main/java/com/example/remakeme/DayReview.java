package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**DayReview activity.*/
public class DayReview extends AppCompatActivity {

  String date = "Meme 2.0";
  static final String dateMessage = "Meme";

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
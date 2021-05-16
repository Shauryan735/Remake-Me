package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Page for inputting detailed notes for an event.
 * May not be needed, consider deleting.
 */
public class Notes extends AppCompatActivity {

  private static final String TAG = "CalendarActivity";
  public static String DATE_MESSAGE = "Meme";
  public static String date = "Meme 2.0";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);

    Intent intent = getIntent();
    date = intent.getStringExtra(DATE_MESSAGE);

  }

  // TODO: Navigation bar


}
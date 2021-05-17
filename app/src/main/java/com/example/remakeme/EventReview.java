package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import java.text.MessageFormat;
import java.util.Calendar;

/**Displays the eventReview activity.*/
public class EventReview extends AppCompatActivity {

  private long eventId;
  private Event event;
  String eventMessage = "event_key";
  private EventDao eventDao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_review);

    AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();

    Intent intent = getIntent();
    eventId = intent.getLongExtra(eventMessage, 0);
    event = getEventById(eventId);

    TextView title = findViewById(R.id.eventReviewTitle);
    title.setText(MessageFormat.format(
        "{0}{1}", event.getEventName(), getString(R.string.review)));

    SeekBar simpleSeekBar;
    simpleSeekBar = findViewById(R.id.seekBar);
    TextView progressVal = findViewById(R.id.eventReviewSeekBarValue);
    simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      int progressChangedValue = 0;

      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressChangedValue = progress;
      }

      public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
      }

      public void onStopTrackingTouch(SeekBar seekBar) {
        /*Toast.makeText(EventReview.this, "Seek bar progress is :" + progressChangedValue,
            Toast.LENGTH_SHORT).show();*/

        progressVal.setText(MessageFormat.format("{0}{1}{2}", getString(R.string.grade),
            progressChangedValue, getString(R.string.of100)));
      }
    });

    Button button = findViewById(R.id.eventReviewBackButton);
    button.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.black));
  }

  public void back(View view) {
    finish();
  }

  public void saveReview(View view) {
  }

  private Event getEventById(long id) {
    /*AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();
    long[] ids = new long[]{id};
    return eventDao.getById(ids).get(0);*/

    Calendar start1 = Calendar.getInstance();
    Calendar end1 = Calendar.getInstance();
    end1.add(Calendar.MINUTE, 10);
    Event event = new Event();
    event.setEventName("Event1");
    event.setEventStart(start1);
    event.setEventEnd(end1);
    event.setGroupColor(0xFFFFA500);
    event.setLocation("location1");
    event.setGraded(true);
    event.setNote("Note");
    return event;
  }
}
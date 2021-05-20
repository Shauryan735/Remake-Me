package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import java.text.MessageFormat;

/**Displays the eventReview activity.*/
public class EventReview extends AppCompatActivity {

  private long eventId;
  private Event event;
  String eventMessage = "event_key";
  private EventDao eventDao;
  private int grade;

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

    EditText note = findViewById(R.id.eventReviewNote);
    if (!(event.getReviewNote() == null)) {
      note.setText(event.getReviewNote());
    }

    SeekBar simpleSeekBar;
    simpleSeekBar = findViewById(R.id.seekBar);
    TextView progressVal = findViewById(R.id.eventReviewSeekBarValue);
    grade = event.getGrade();
    if (grade != 0) {
      progressVal.setText(MessageFormat.format("{0}{1}{2}", getString(R.string.grade),
          grade, getString(R.string.of100)));
      simpleSeekBar.setProgress(grade);
    }

    simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      int progressChangedValue = 0;

      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressChangedValue = progress;
        if (progressChangedValue == 0) {
          progressChangedValue = 1;
        }
      }

      public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
      }

      public void onStopTrackingTouch(SeekBar seekBar) {
        /*Toast.makeText(EventReview.this, "Seek bar progress is :" + progressChangedValue,
            Toast.LENGTH_SHORT).show();*/

        progressVal.setText(MessageFormat.format("{0}{1}{2}", getString(R.string.grade),
            progressChangedValue, getString(R.string.of100)));
        grade = progressChangedValue;
      }
    });

    Button button = findViewById(R.id.eventReviewBackButton);
    button.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.black));
  }

  public void back(View view) {
    Intent intent = new Intent(this, EventView.class);
    intent.putExtra(eventMessage, eventId);
    startActivity(intent);
    finish();
  }

  /**Saves the review and returns to the eventView.*/
  public void saveReview(View view) {
    event.setGrade(grade);
    EditText note = findViewById(R.id.eventReviewNote);
    String reviewNote;
    try {
      reviewNote = note.getText().toString();
    } catch (Exception e) {
      reviewNote = "None";
    }
    event.setReviewNote(reviewNote);

    eventDao.updateEvent(event);
    Intent intent = new Intent(this, EventView.class);
    intent.putExtra(eventMessage, eventId);
    startActivity(intent);
    finish();
  }

  private Event getEventById(long id) {
    AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();
    long[] ids = new long[]{id};
    return eventDao.getById(ids).get(0);

    /*Calendar start1 = Calendar.getInstance();
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
    return event;*/
  }
}
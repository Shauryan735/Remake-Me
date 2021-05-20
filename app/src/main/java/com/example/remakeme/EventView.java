package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import java.text.MessageFormat;

/**Displays the eventView activity for a certain event.*/
public class EventView extends AppCompatActivity {

  private long eventId;
  private Event event;
  String eventMessage = "event_key";
  private EventDao eventDao;
  public static String DATE_MESSAGE = "Meme";

  NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  //TODO: consolidate a lot of onCreate into a method that can be called with a button or triggered

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_view);

    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nav = findViewById(R.id.nav);
    drawerLayout = findViewById(R.id.drawer);

    AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();

    toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    nav.setNavigationItemSelectedListener(item -> {

      switch (item.getItemId()) {
        case R.id.navmenu_home:
          drawerLayout.closeDrawer(GravityCompat.START);
          finish();
          break;
        case R.id.navmenu_dayView:
          finish();
          break;
        case R.id.navmenu_newEvent:
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent event = new Intent(EventView.this, AddEvent.class);
          startActivity(event);
          finish();
          break;
        case R.id.navmenu_infographics:
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent info = new Intent(EventView.this, Infographics.class);
          startActivity(info);
          finish();
          break;
        case R.id.navmenu_reflection:
        default:
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent reflect = new Intent(EventView.this, DailyReflection.class);
          startActivity(reflect);
          finish();
          break;
      }
      return true;
    });

    Intent intent = getIntent();
    eventId = intent.getLongExtra(eventMessage, 0);
    event = getEventById(eventId);

    TextView eventNameView = findViewById(R.id.eventViewName);
    eventNameView.setText(event.getEventName());

    TextView eventDateDataView = findViewById(R.id.eventViewDateData);
    eventDateDataView.setText(Event.getFormattedDate(event.getEventStart()));

    TextView eventStartTimeDataView = findViewById(R.id.eventViewStartTimeData);
    eventStartTimeDataView.setText(event.getFormattedStartTime());

    TextView eventEndTimeDataView = findViewById(R.id.eventViewEndTimeData);
    eventEndTimeDataView.setText(event.getFormattedEndTime());

    TextView eventLocationDataView = findViewById(R.id.eventViewLocationData);
    eventLocationDataView.setText(event.getLocation());

    TextView eventNoteDataView = findViewById(R.id.eventViewNoteData);
    eventNoteDataView.setText(event.getNote());

    Button button = findViewById(R.id.eventViewBackButton);
    button.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.black));

    TextView gradeVal = findViewById(R.id.eventViewGradeData);
    gradeVal.setText(MessageFormat.format("{0}{1}{2}", getString(R.string.grade),
        event.getGrade(), getString(R.string.of100)));

    TextView reflectNote = findViewById(R.id.eventViewReflectNoteData);
    reflectNote.setText(event.getReviewNote());

    if (!event.isGraded()) {
      Button reviewButton = findViewById(R.id.eventViewReflectButton);
      reviewButton.setEnabled(false);
      reviewButton.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.gray));
      TextView grade = findViewById(R.id.eventViewGrade);
      grade.setTextColor(0);
      TextView reflection = findViewById(R.id.eventViewReflectNote);
      reflection.setTextColor(0);
      gradeVal.setTextColor(0);
      reflectNote.setTextColor(0);
    }

    View relativeLayout = findViewById(R.id.eventView);
    relativeLayout.setBackgroundResource(event.getGroupColoredOutline());
  }

  public static Intent makeIntent(Context context) {
    return new Intent(context, EventView.class);
  }

  /**When the corresponding button is pushed, the addEvent activity is intiated in edit mode.*/
  public void editEvent(View view) {
    Intent intent = new Intent(EventView.this, AddEvent.class);
    intent.putExtra(eventMessage, eventId);
    startActivity(intent);
    finish();
  }

  /**When the corresponding button is pushed, the current event is removed from the database.*/
  public void deleteEvent(View view) {
    Intent intent = new Intent(EventView.this, DayViewV2.class);
    intent.putExtra(DATE_MESSAGE, Event.getFormattedDate(event.getEventStart()));
    eventDao.delete(event);
    startActivity(intent);
    finish();
  }

  private Event getEventById(long id) {
    AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();
    long[] ids = new long[]{id};
    return eventDao.getById(ids).get(0);
  }

  /**When the corresponding button is pushed, the reflectEvent activity is called.*/
  public void reflectEvent(View view) {
    if (!event.isGraded()) {
      Toast.makeText(getApplicationContext(),
              event.getEventName() + " is not a graded event.", Toast.LENGTH_LONG)
              .show();
      return;
    }
    Intent intent = new Intent(EventView.this, EventReview.class);
    intent.putExtra(eventMessage, eventId);
    startActivity(intent);
  }

  public void back(View view) {
    finish();
  }

}
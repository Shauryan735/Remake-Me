package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Main calendar page.
 * Can navigate to the day view by clicking any day.
 */
public class MainActivity extends AppCompatActivity {

  NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  public static String DATE_MESSAGE = "Meme";
  public static String date = "4/30/2021";
  public EventDao eventDao;
  public AppDatabase database;
  public int start = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nav = findViewById(R.id.nav);
    drawerLayout = findViewById(R.id.drawer);

    toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    date = df.format(c);

    nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.navmenu_home) {
          drawerLayout.closeDrawer(GravityCompat.START);
        } else if (itemId == R.id.navmenu_dayView) {
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent day = new Intent(MainActivity.this, DayViewV2.class);
          day.putExtra(DATE_MESSAGE, date);
          startActivity(day);
        } else if (itemId == R.id.navmenu_newEvent) {
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent event = new Intent(MainActivity.this, AddEvent.class);
          event.putExtra(DATE_MESSAGE, MainActivity.date);
          startActivity(event);
        } else if (itemId == R.id.navmenu_infographics) {
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent info = new Intent(MainActivity.this, Infographics.class);
          info.putExtra(DATE_MESSAGE, MainActivity.date);
          startActivity(info);
        } else {
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent reflect = new Intent(MainActivity.this, DailyReflection.class);
          reflect.putExtra(DATE_MESSAGE, MainActivity.date);
          startActivity(reflect);
        }
        return true;
      }
    });


    if (start == 0) {
      // Create database and eventDao
      //TODO: define behavior
    }
    start = 1;

    CalendarView calendarView = findViewById(R.id.calender);

    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
      @Override
      public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
        month = month + 1;
        date = month + "/" + day + "/" + year;
        Intent intent = DayViewV2.makeIntent(MainActivity.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
      }
    });
  }

  /**
   * Sets the date equal to the current actual date.
   */
  public void setDate(View view) {
    CalendarView simpleCalendarView = findViewById(R.id.calender);
    long selectedDate = simpleCalendarView.getDate();
    simpleCalendarView.setDate(selectedDate);
  }


  /*Toast.makeText(getApplicationContext(),
          "Click ListItem Number " + position, Toast.LENGTH_LONG)
          .show();
       sample code for "printing"*/
}
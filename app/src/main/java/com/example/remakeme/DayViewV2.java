package com.example.remakeme;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.Calendar;
import java.util.List;

/**
 * Displays the current date selected and events on that day.
 */
public class DayViewV2 extends AppCompatActivity {

  final static String dateMessage = "Meme";
  String date = "Meme 2.0";
  final String eventMessage = "event_key";
  final Context context = this;

  NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  public static String getDateMessage() {
    return dateMessage;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_day_view_v2);

    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nav = findViewById(R.id.nav);
    drawerLayout = findViewById(R.id.drawer);

    toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
          case R.id.navmenu_home:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(DayViewV2.this, MainActivity.class);
            startActivity(intent);
            finish();
            break;
          case R.id.navmenu_dayView:
            drawerLayout.closeDrawer(GravityCompat.START);
            break;
          case R.id.navmenu_newEvent:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent event = new Intent(DayViewV2.this, AddEvent.class);
            startActivity(event);
            finish();
            break;
          case R.id.navmenu_infographics:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent info = new Intent(DayViewV2.this, Infographics.class);
            startActivity(info);
            finish();
            break;
          case R.id.navmenu_reflection:
          default:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent reflect = new Intent(DayViewV2.this, DailyReflection.class);
            startActivity(reflect);
            finish();
            break;
        }
        return true;
      }
    });

    Toolbar toolbar2 = findViewById(R.id.dayViewToolbar);
    setSupportActionBar(toolbar2);
    ActionBar ab = getSupportActionBar();
    assert ab != null;
    ab.setDisplayHomeAsUpEnabled(true);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = AddEvent.makeIntent(context);
        intent.putExtra(dateMessage, date);
        startActivity(intent);
        finish();
      }
    });

    Intent intent = getIntent();
    date = intent.getStringExtra(dateMessage);

    TextView textView = findViewById(R.id.textView);
    textView.setText(date);

    String[] dateParts = date.split("/");
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, parseInt(dateParts[0]) - 1);
    calendar.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
    calendar.set(Calendar.YEAR, parseInt(dateParts[2]));
    Calendar prevDay = calendar;
    prevDay.add(Calendar.HOUR, -24);
    String prevDate = Event.getFormattedDate(prevDay);

    Button prevDayButton = findViewById(R.id.prevDay);
    prevDayButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = DayViewV2.makeIntent(context);
        intent.putExtra(dateMessage, prevDate);
        startActivity(intent);
        finish();
      }
    });

    Calendar nextDay = calendar;
    nextDay.add(Calendar.HOUR, 48);
    String nextDate = Event.getFormattedDate(nextDay);

    Button nextDayButton = findViewById(R.id.nextDay);
    nextDayButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = DayViewV2.makeIntent(context);
        intent.putExtra(dateMessage, nextDate);
        startActivity(intent);
        finish();
      }
    });

    AppDatabase instance = AppDatabase.getInstance(this);
    EventDao eventDao = instance.getEventDao();
    //"YYYY-MM-DD"
    Calendar calendar2 = Calendar.getInstance();
    calendar2.set(Calendar.MONTH, parseInt(dateParts[0]) - 1);
    calendar2.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
    calendar2.set(Calendar.YEAR, parseInt(dateParts[2]));

    List<Event> dayEvents = eventDao.getNonLiveByDay(Event.getDbFormattedDate(calendar2));


    //create testing events
    /*Calendar start1 = Calendar.getInstance();
       Calendar start2 = Calendar.getInstance();
       start2.add(Calendar.HOUR, 1);
       Calendar start3 = Calendar.getInstance();
       start3.add(Calendar.HOUR, 2);
       Calendar start4 = Calendar.getInstance();
       start4.add(Calendar.HOUR, 3);
       Calendar end1 = Calendar.getInstance();
       end1.add(Calendar.MINUTE, 10);
       Calendar end2 = Calendar.getInstance();
       end2.add(Calendar.MINUTE, 70);
       Calendar end3 = Calendar.getInstance();
       end3.add(Calendar.MINUTE, 130);
       Calendar end4 = Calendar.getInstance();
       end4.add(Calendar.MINUTE, 190);

       ArrayList<Event> dayEvents = new ArrayList<Event>();
       dayEvents.add(new Event("Event1", start1, end1,
               0xFFFFA500, "location1", false, false, "none"));
       dayEvents.add(new Event("Event2", start2, end2,
               0xFFFFFF00, "location2", false, false, "none"));
       dayEvents.add(new Event("Event3", start3, end3,
               0xFF008000, "location3", false, false, "none"));
       dayEvents.add(new Event("Event4", start4, end4,
               0xFF0000FF, "location4", false, false, "none"));*/

    final EventArrayAdapter adapter = new EventArrayAdapter(dayEvents, getApplicationContext());
    ListView listView = findViewById(R.id.listView);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {
        Intent intent = EventView.makeIntent(context);
        intent.putExtra(eventMessage, dayEvents.get(position).getId());
        startActivity(intent);
        finish();
      }
    });
  }

  public static Intent makeIntent(Context context) {
    return new Intent(context, DayViewV2.class);
  }
}
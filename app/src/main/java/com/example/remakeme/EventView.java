package com.example.remakeme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class EventView extends AppCompatActivity {

    private long eventId;
    String EVENT_MESSAGE = "event_key";
    private EventDao eventDao;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent reflect = new Intent(EventView.this, DailyReflection.class);
                        startActivity(reflect);
                        finish();
                        break;
                }
                return true;
            }
        });

        TextView eventNameView = findViewById(R.id.eventViewName);
        /*TextView eventDateView = findViewById(R.id.eventViewDate);*/
        TextView eventDateDataView = findViewById(R.id.eventViewDateData);
        /*TextView eventStartTimeView = findViewById(R.id.eventViewStartTime);*/
        TextView eventStartTimeDataView = findViewById(R.id.eventViewStartTimeData);
        /*TextView eventEndTimeView = findViewById(R.id.eventViewEndTime);*/
        TextView eventEndTimeDataView = findViewById(R.id.eventViewEndTimeData);
        /*TextView eventLocationView = findViewById(R.id.eventViewLocation);*/
        TextView eventLocationDataView = findViewById(R.id.eventViewLocationData);
        /*TextView eventNoteView = findViewById(R.id.eventViewNote);*/
        TextView eventNoteDataView = findViewById(R.id.eventViewNoteData);

        Intent intent = getIntent();
        eventId = intent.getLongExtra(EVENT_MESSAGE, 0);
        Event event = getEventById(eventId);
        eventNameView.setText(event.getEventName());
        eventDateDataView.setText(Event.getFormattedDate(event.getEventStart()));
        eventStartTimeDataView.setText(event.getFormattedStartTime());
        eventEndTimeDataView.setText(event.getFormattedEndTime());
        eventLocationDataView.setText(event.getLocation());
        eventNoteDataView.setText(event.getNote());

        View relativeLayout = findViewById(R.id.eventView);
        relativeLayout.setBackgroundResource(event.getGroupColoredOutline());
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, EventView.class);
    }

    public void editEvent(View view){
        Intent intent = new Intent(EventView.this, AddEvent.class);
        eventId = 1;
        intent.putExtra(EVENT_MESSAGE, eventId);
        startActivity(intent);
    }

    public void deleteEvent(View view){
        /*dummy*/
        //TODO: add deleteEvent method
    }

    private Event getEventById(long id){
        AppDatabase instance = AppDatabase.getInstance(this);
        eventDao = instance.getEventDao();
        long[] ids = new long[]{id};
        return eventDao.getById(ids).get(0);

        /*Calendar start1 = Calendar.getInstance();
        Calendar end1 = Calendar.getInstance();
        end1.add(Calendar.MINUTE, 10);
        return new Event("Cool Event", start1, end1, 0xFFFFA500, "San Luis Obispo", false, 0, false, "Really cool note");*/
    }

}
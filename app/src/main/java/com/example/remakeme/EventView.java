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

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        // TODO: Start of Navigation bar code
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
        // TODO: End of Navigation bar code

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

    //TODO: add navBar functionality

    public static Intent makeIntent(Context context) {
        return new Intent(context, EventView.class);
    }

    public void editEvent(View view){
        /*dummy*/
        //TODO: add editEvent method
    }
    public void deleteEvent(View view){
        /*dummy*/
        //TODO: add deleteEvent method
    }

    private Event getEventById(long id){
        //TODO: access event from database

        Calendar start1 = Calendar.getInstance();
        Calendar end1 = Calendar.getInstance();
        end1.add(Calendar.MINUTE, 10);
        return new Event("Event1", start1, end1,
                0xFFFFA500, "location1 but i want a really long string that will " +
                "go outside the box for testing purposes help me", false, false,
                "really long testing note that is well beyond the length of the box I made to " +
                        "see how overflow works, if at allllllllll");
    }

}
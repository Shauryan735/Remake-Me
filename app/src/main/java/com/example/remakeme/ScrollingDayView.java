package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
//TODO: remove ScrollingDayView.class

public class ScrollingDayView extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dayViewToolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //TODO: replace onClick method body with a call to addEvent activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(date);

        //TODO: add left and right swipe navigation between dayViews
        /*String[] dateParts = date.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, parseInt(dateParts[0]));
        calendar.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
        calendar.set(Calendar.YEAR, parseInt(dateParts[2]));
        Calendar left = calendar;
        left.add(Calendar.HOUR, -24);
        Calendar right = calendar;
        right.add(Calendar.HOUR, 24);*/
        //TODO: add left and right buttons that call Dayview with left and right dates



        ListView listView = findViewById(R.id.listView);

        String[] dates = new String[] {date};
        /*Event[] events = EventDao.getByDates(dates);
        final ArrayList<Event> dayEvents = new ArrayList<Event>();
        for (int i = 0; i < events.length; ++i) {
            dayEvents.add(events[i]);
        }*/


        Calendar start1 = Calendar.getInstance();
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
                0xFF99FFFF, "location1", false, false, "none"));
        dayEvents.add(new Event("Event2", start2, end2,
                0xFF998DFF, "location2", false, false, "none"));
        dayEvents.add(new Event("Event3", start3, end3,
                0xFF99FFBC, "location3", false, false, "none"));
        dayEvents.add(new Event("Event4", start4, end4,
                0xFFFF69BC, "location4", false, false, "none"));

        final EventArrayAdapter adapter = new EventArrayAdapter(dayEvents, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                //TODO: add eventView activity in response to this listener
            }
        });

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScrollingDayView.class);
    }

}
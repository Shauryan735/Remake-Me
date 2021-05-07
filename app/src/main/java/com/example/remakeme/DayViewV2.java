package com.example.remakeme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DayViewV2 extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";
    String EVENT_MESSAGE = "event_key";
    Context context = this;
    private EventDao eventDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view_v2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dayViewToolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddEvent.makeIntent(context);
                intent.putExtra(DATE_MESSAGE, date);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);
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

        Button prevDayButton = (Button) findViewById(R.id.prevDay);
        prevDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DayViewV2.makeIntent(context);
                intent.putExtra(DATE_MESSAGE, prevDate);
                startActivity(intent);
                finish();
            }
        });

        Calendar nextDay = calendar;
        nextDay.add(Calendar.HOUR, 48);
        String nextDate = Event.getFormattedDate(nextDay);

        Button nextDayButton = (Button) findViewById(R.id.nextDay);
        nextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DayViewV2.makeIntent(context);
                intent.putExtra(DATE_MESSAGE, nextDate);
                startActivity(intent);
                finish();
            }
        });

        ListView listView = findViewById(R.id.listView);

        /*Event[] events = EventDao.getByDates(dates);
        final ArrayList<Event> dayEvents = new ArrayList<Event>();
        for (int i = 0; i < events.length; ++i) {
            dayEvents.add(events[i]);
        }*/
        //TODO: get events from database

        AppDatabase instance = AppDatabase.getInstance(this);
        eventDao = instance.getEventDao();
        //"YYYY-MM-DD"
        String[] dateParts2 = date.split("/");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.MONTH, parseInt(dateParts[0]) - 1);
        calendar2.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
        calendar2.set(Calendar.YEAR, parseInt(dateParts[2]));
        /*List<Event> events = eventDao.getByDay(Event.getDBFormattedDate(calendar2)).getValue();*/


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
                0xFFFFA500, "location1", false, false, "none"));
        dayEvents.add(new Event("Event2", start2, end2,
                0xFFFFFF00, "location2", false, false, "none"));
        dayEvents.add(new Event("Event3", start3, end3,
                0xFF008000, "location3", false, false, "none"));
        dayEvents.add(new Event("Event4", start4, end4,
                0xFF0000FF, "location4", false, false, "none"));
        dayEvents.add(new Event("Event5", start1, end1,
                0xFFFFA500, "location1", false, false, "none"));
        dayEvents.add(new Event("Event6", start2, end2,
                0xFFFFFF00, "location2", false, false, "none"));
        dayEvents.add(new Event("Event7", start3, end3,
                0xFF008000, "location3", false, false, "none"));
        dayEvents.add(new Event("Event8", start4, end4,
                0xFF0000FF, "location4", false, false, "none"));

        final EventArrayAdapter adapter = new EventArrayAdapter(dayEvents, getApplicationContext());
        /*if(adapter == null){
            Toast.makeText(getApplicationContext(),
                    "null adapter", Toast.LENGTH_LONG)
                    .show();
        }*/
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /*Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();*/
                Intent intent = EventView.makeIntent(context);
                intent.putExtra(EVENT_MESSAGE, dayEvents.get(position).getId());
                startActivity(intent);
            }
        });

    }

    //TODO: add navBar functionality (and in .xml file)

    public static Intent makeIntent(Context context) {
        return new Intent(context, DayViewV2.class);
    }
}
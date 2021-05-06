package com.example.remakeme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class DayViewV2 extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";
    Context context = this;

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

        //TODO: add left and right swipe navigation between dayViews
        String[] dateParts = date.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, parseInt(dateParts[0]));
        calendar.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
        calendar.set(Calendar.YEAR, parseInt(dateParts[2]));
        Calendar left = calendar;
        left.add(Calendar.HOUR, -24);
        Calendar right = calendar;
        right.add(Calendar.HOUR, 24);



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
        if(adapter == null){
            Toast.makeText(getApplicationContext(),
                    "null adapter", Toast.LENGTH_LONG)
                    .show();
        }
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
        return new Intent(context, DayViewV2.class);
    }
}
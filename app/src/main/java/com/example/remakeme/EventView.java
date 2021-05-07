package com.example.remakeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class EventView extends AppCompatActivity {

    private long eventId;
    String EVENT_MESSAGE = "event_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

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
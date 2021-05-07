package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    private EventDao eventDao;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";
    String groupColor = "Red";
    String repeat = "Never";
    String reminder = "Never";
    String location = "";
    String title = "New Event";
    String notes = "";
    Calendar eventDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        EditText editDate = findViewById(R.id.editTextDate);
        editDate.setText(date);

        AppDatabase instance = AppDatabase.getInstance(this);
        eventDao = instance.getEventDao();

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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent day = new Intent(AddEvent.this, DayViewV2.class);
                        day.putExtra(DATE_MESSAGE, date);
                        startActivity(day);
                        finish();
                        break;
                    case R.id.navmenu_newEvent:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navmenu_infographics:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent info = new Intent(AddEvent.this, Infographics.class);
                        startActivity(info);
                        finish();
                        break;
                    case R.id.navmenu_reflection:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent reflect = new Intent(AddEvent.this, DailyReflection.class);
                        startActivity(reflect);
                        finish();
                        break;
                }
                return true;
            }
        });

        // TODO: End of Navigation bar code


        Spinner spinner = findViewById(R.id.spinnerColor);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Red");
        arrayList.add("Orange");
        arrayList.add("Yellow");
        arrayList.add("Green");
        arrayList.add("Blue");
        arrayList.add("Purple");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groupColor = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner repeatSpinner = findViewById(R.id.spinnerRepeat);
        ArrayList<String> repeatList = new ArrayList<>();
        repeatList.add("Never");
        repeatList.add("Daily");
        repeatList.add("Weekly");
        repeatList.add("Monthly");
        repeatList.add("Annually");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repeatList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(arrayAdapter2);
        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repeat = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner reminderSpinner = findViewById(R.id.spinnerReminder);
        ArrayList<String> reminderList = new ArrayList<>();
        reminderList.add("Never");
        reminderList.add("0 minutes before");
        reminderList.add("5 minutes before");
        reminderList.add("15 minutes before");
        reminderList.add("30 minutes before");
        reminderList.add("1 hour before");

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reminderList);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminderSpinner.setAdapter(arrayAdapter3);
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reminder = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void submit(View view) {
        String calendar = "Calendar";
        boolean boolRepeat = false;
        boolean boolReminder = false;
        int color = 0;
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        EditText editTitle = findViewById(R.id.editTextName);
        try {
            title = editTitle.getText().toString();
        }
        catch (Exception e) {
            title = "New Event";
        }

        EditText editDate = findViewById(R.id.editTextDate);
        date = editDate.getText().toString();
        String[] numbers = date.split("/");
        int year = Integer.parseInt(numbers[2]);
        int month = Integer.parseInt(numbers[0]);
        int day = Integer.parseInt(numbers[1]);

        EditText editLocation = findViewById(R.id.editTextLocation);
        try {
            location = editLocation.getText().toString();
        }
        catch (Exception e) {
            location = "None";
        }

        EditText editNotes = findViewById(R.id.editTextNotes);
        try {
            notes = editNotes.getText().toString();
        }
        catch (Exception e) {
            notes = "None";
        }

        EditText editStart = findViewById(R.id.editTextStartTime);
        String[] startTime = editStart.getText().toString().split(":");
        int startHour = Integer.parseInt(startTime[0]);
        int startMinute = Integer.parseInt(startTime[1]);
        startCalendar.set(year, month, day, startHour, startMinute);

        EditText editEnd = findViewById(R.id.editTextEndTime);
        String[] endTime = editEnd.getText().toString().split(":");
        int endHour = Integer.parseInt(endTime[0]);
        int endMinute = Integer.parseInt(endTime[1]);
        endCalendar.set(year, month, day, endHour, endMinute);

        if (!(repeat.equals("Never")))
            boolRepeat = true;

        if (!(reminder.equals("Never")))
            boolReminder = true;

        switch (groupColor) {
            case "Orange":
                color = 0xFFFFA500;
                break;
            case "Yellow":
                color = 0xFFFFFF00;
                break;
            case "Green":
                color = 0xFF008000;
                break;
            case "Blue":
                color = 0xFF0000FF;
                break;
            case "Purple":
                color = 0xFF800080;
                break;
            case "Red":
            default:
                color = 0xFFFF0000;
                break;
        }


        Event event = new Event(title, startCalendar, endCalendar, color, location, boolRepeat, boolReminder, notes);
        long event_id = eventDao.insert(event);
        if(boolRepeat){
            repeat(event, event_id);
        }
        NotificationPublisher.scheduleEventNotification(this, event);

        /**instead of starting a new activity, simply destroy this one, forcing a return to the previous view
         * (I'm not sure how to do that)**/
        Intent intent = new Intent(this, DayView.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    // TODO: Navigation bar helper code

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddEvent.class);
    }

    public void repeat(Event e, long baseId)
    {
        String name = e.getEventName();
        Calendar startTime = e.getEventStart();
        Calendar endTime = e.getEventEnd();
        Boolean remind = e.getSendReminders();
        Calendar remindTime = e.getRemindTime();
        Boolean repeat = e.getRepeat(); 							    //repeat offset is being treated as follows:
        int repeatOffset = e.getRepeatOffset();						    //Daily:    1         |Weekly:   2
        //BiWeekly: 3         |Monthly:  4
        if(repeat)
        {
            if(repeatOffset == 1)									    //If repeat set to Daily create new events one year out
            {														    //
                int count = 0;										    //count init at 0 for one year of events
                while(count < 365)									    //check if count is still less than 365
                {													    //
                    startTime.add(Calendar.DAY_OF_YEAR, count+1);	//update the calendar with new time from repeat loop
                    endTime.add(Calendar.DAY_OF_YEAR, count+1);	    //update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(baseId,name, startTime, endTime,   //
                            remind,remindTime,repeat,repeatOffset);	    //Create a new event with the new calendars
                    eventDao.insert(ne);
                    count+=1;										    //increment the event counter
                }
            }
            else if(repeatOffset == 2)								        //If repeat set to Weekly create new events one year out
            {														    //
                int count = 0;										    //count init at 0 for one year of events
                while(count < 365)									    //check if count is still less than 365
                {													    //
                    startTime.add(Calendar.DAY_OF_YEAR, count+7);	//update the calendar with new time from repeat loop
                    endTime.add(Calendar.DAY_OF_YEAR, count+7);	//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(baseId,name, startTime, endTime,   //
                            remind,remindTime,repeat,repeatOffset);	    //Create a new event with the new calendars
                    eventDao.insert(ne);
                    count+=7;										    //increment the event counter by seven days
                }
            }
            else if(repeatOffset == 3)								    //If repeat set to BiWeekly create new events one year out
            {														    //
                int count = 0;										    //count init at 0 for one year of events
                while(count < 365)									    //check if count is still less than 365
                {													    //
                    startTime.add(Calendar.DAY_OF_YEAR, count+14);	//update the calendar with new time from repeat loop
                    endTime.add(Calendar.DAY_OF_YEAR, count+14);	//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(baseId,name, startTime, endTime,   //
                            remind,remindTime,repeat,repeatOffset);	    //Create a new event with the new calendars
                    eventDao.insert(ne);
                    count+=14;										    //increment the event counter by seven days
                }
            }
            else if(repeatOffset == 4)								    //If repeat set to Monthly create new events one year out
            {														    //
                int count = 0;										    //count init at 0 for one year of events
                while(count < 12)									    //check if count is still less than 365
                {													    //
                    startTime.add(Calendar.MONTH, count+1);		//update the calendar with new time from repeat loop
                    endTime.add(Calendar.MONTH, count+1);			//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(baseId,name, startTime, endTime,   //
                            remind,remindTime,repeat,repeatOffset);	    //Create a new event with the new calendars
                    eventDao.insert(ne);
                    count+=1;										    //increment the event counter by seven days
                }
            }
            else if(repeatOffset == 5)								    //If repeat set to Yearly create an event one year out
            {														    //
                startTime.add(Calendar.YEAR, 1);					//update the calendar with new time
                endTime.add(Calendar.YEAR, 1);						//update the calendar with new time
                //
                Event ne = new Event(baseId,name, startTime, endTime,       //
                        remind,remindTime,repeat,repeatOffset);	        //Create a new event with the new calendars
                eventDao.insert(ne);
            }
        }
    }
}
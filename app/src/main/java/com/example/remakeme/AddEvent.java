package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEvent extends AppCompatActivity {

    private EventDao eventDao;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";
    String EVENT_MESSAGE = "event_key";

    String groupColor = "Red";
    String repeat = "Never";
    String reminder = "Never";
    String location = "";
    String title = "New Event";
    String notes = "";
    Calendar eventDate;
    Boolean editing = false;
    long event_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        try {
            event_id = intent.getLongExtra(EVENT_MESSAGE, 0);
            if (event_id != 0) {
                editing = true;
                Button submitButton = findViewById(R.id.eventViewEditButton);
                submitButton.setText("Edit Event");
            }
        }
        catch (Exception e) {}

        EditText editDate = findViewById(R.id.editTextDate);
        editDate.setText(date);

        AppDatabase instance = AppDatabase.getInstance(this);
        eventDao = instance.getEventDao();
        // eventDao.clearAll(); // Uncomment to clear database

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

        long[] events = {event_id};

        if (editing) {
            EditText editName = findViewById(R.id.editTextName);
            Button button = findViewById(R.id.eventViewEditButton);
            button.setText(R.string.editEvent);


            List eventList = eventDao.getById(events);
            Event editEvent = (Event) eventList.get(0);

            // Switch to below for basic testing

            /* Calendar start1 = Calendar.getInstance();
            Calendar end1 = Calendar.getInstance();
            Event editEvent = new Event("Cool Event", start1, end1, 0xFFFFA500, "San Luis Obispo", false, 0, false, "Really cool note");
            */

            editName.setText(editEvent.getEventName());

            Calendar gotDate = editEvent.getEventStart();
            Date c = gotDate.getTime();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String stringDate = df.format(c);
            editDate.setText(stringDate);

            EditText editStartTime = findViewById(R.id.editTextStartTime);
            editStartTime.setText(editEvent.getFormattedStartTime());
            EditText editEndTime = findViewById(R.id.editTextEndTime);
            editEndTime.setText(editEvent.getFormattedEndTime());

            String hexColor;
            switch (editEvent.getGroupColor()) {
                case 0xFFFFA500:
                    hexColor = "Orange";
                    break;
                case 0xFFFFFF00:
                    hexColor = "Yellow";
                    break;
                case 0xFF008000:
                    hexColor = "Green";
                    break;
                case 0xFF0000FF:
                    hexColor = "Blue";
                    break;
                case 0xFF800080:
                    hexColor = "Purple";
                    break;
                case 0xFFFF0000:
                default:
                    hexColor = "Red";
                    break;
            }
            spinner.setSelection(arrayAdapter.getPosition(hexColor));

            EditText editLocation = findViewById(R.id.editTextLocation);
            editLocation.setText(editEvent.getLocation());

            repeatSpinner.setSelection(editEvent.getRepeatOffset());
            repeatSpinner.setSelection(0);

            EditText editNotes = findViewById(R.id.editTextNotes);
            editNotes.setText(editEvent.getNote());
        }
    }

    public void submit(View view) {
        boolean boolRepeat = false;
        boolean boolReminder = false;
        int repeatOffset = 0;
        int color;
        int month;
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        EditText editTitle = findViewById(R.id.editTextName);
        try {
            title = editTitle.getText().toString();
            if (title.equals("")) {
                title = "New Event";
            }
        }
        catch (Exception e) {
            title = "New Event";
        }

        EditText editDate = findViewById(R.id.editTextDate);
        date = editDate.getText().toString();
        String[] numbers = date.split("/");
        int year = Integer.parseInt(numbers[2]);
        month = Integer.parseInt(numbers[0]) - 1;
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

        if (!(repeat.equals("Never"))) {
            boolRepeat = true;
            switch (repeat) {
                case "Daily":
                    repeatOffset = 1;
                    break;
                case "Weekly":
                    repeatOffset = 2;
                    break;
                case "Biweekly":
                    repeatOffset = 3;
                    break;
                case "Monthly":
                    repeatOffset = 4;
                    break;
                default:
                    repeatOffset = 0;
                    break;
            }
        }

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

        Event event = new Event(title, startCalendar, endCalendar, color, location, boolRepeat, repeatOffset, boolReminder, notes);
        Toast.makeText(getApplicationContext(),
                event.getEventStart().get(Calendar.MONTH) + "/" + event.getEventStart().get(Calendar.DATE), Toast.LENGTH_LONG)
                .show();

        if (!editing) {
            event_id = eventDao.insert(event);
            if (boolRepeat) {
                repeat(event_id);
            }
            event.setId(event_id);
            //NotificationPublisher.scheduleEventNotification(this, event);
            if(event.getSendReminders()) {
            NotificationPublisher.scheduleEventNotification(this, event);
            }

            /**instead of starting a new activity, simply destroy this one, forcing a return to the previous view
             * (I'm not sure how to do that)**/
            Intent intent = new Intent(this, DayViewV2.class);
            intent.putExtra(DATE_MESSAGE, date);
            startActivity(intent);
            finish();
        }
        else {
            event.setId(event_id);
            eventDao.updateEvent(event);
            Intent intent = new Intent(this, EventView.class);
            intent.putExtra(EVENT_MESSAGE, event_id);
            startActivity(intent);
            finish();
        }
    }

    // TODO: Navigation bar helper code

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddEvent.class);
    }

    private void incrementDayOfYear(Event event, int by){
        Calendar start = event.getEventStart();
        Calendar end = event.getEventEnd();
        start.add(Calendar.DAY_OF_YEAR, by);
        end.add(Calendar.DAY_OF_YEAR, by);
        event.setEventStart(start);
        event.setEventEnd(end);
    }

    public void repeat(long baseId)
    {
        long[] ids = {baseId};
        Event event = eventDao.getById(ids).get(0);
        event.setId(null);
        event.setBaseId(baseId);                    //repeat offset is being treated as follows:
        int repeatOffset = event.getRepeatOffset();	//Daily:    1         |Weekly:   2
        int i = 0;                                  //BiWeekly: 3         |Monthly:  4
        if(repeatOffset == 1)
        {
            while(i < 365)
            {
                incrementDayOfYear(event, 1);
                eventDao.insert(event);
                i+=1;
            }
        }
        else if(repeatOffset == 2)
        {
            while(i < 365)
            {
                incrementDayOfYear(event, 7);
                eventDao.insert(event);
                i+=7;
            }
        }
        else if(repeatOffset == 3)
        {
            while(i < 365)
            {
                incrementDayOfYear(event, 14);
                eventDao.insert(event);
                i+=14;
            }
        }
        else if(repeatOffset == 4)
        {
            while(i < 12)
            {
                Calendar start = event.getEventStart();
                Calendar end = event.getEventEnd();
                start.add(Calendar.MONTH, 1);
                end.add(Calendar.MONTH, 1);
                event.setEventStart(start);
                event.setEventEnd(end);

                eventDao.insert(event);
                i+=1;
            }
        }
        else if(repeatOffset == 5)
        {
            Calendar start = event.getEventStart();
            Calendar end = event.getEventEnd();
            start.add(Calendar.YEAR, 1);
            end.add(Calendar.YEAR, 1);
            event.setEventStart(start);
            event.setEventEnd(end);

            eventDao.insert(event);
        }
    }
}
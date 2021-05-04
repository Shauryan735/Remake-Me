package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

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


        // TODO: Start of Navigation bar code

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.nav);

        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navmenu_home:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navOpenHome();
                    break;

                case R.id.navmenu_dayview:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navOpenDayView();
                    break;

                case R.id.navmenu_newevent:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navOpenEvent();
                    break;

                case R.id.navmenu_infographics:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navOpenInfo();
                    break;

                case R.id.navmenu_reflection:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navOpenReflect();
                    break;
            }
            return true;
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

        EditText editTitle = findViewById(R.id.editTextName);
        title = editTitle.getText().toString();

        EditText editDate = findViewById(R.id.editTextDate);
        date = editDate.getText().toString();

        EditText editLocation = findViewById(R.id.editTextLocation);
        location = editLocation.getText().toString();

        EditText editNotes = findViewById(R.id.editTextNotes);
        notes = editNotes.getText().toString();

        if (!(repeat.equals("Never")))
            boolRepeat = true;

        if (!(reminder.equals("Never")))
            boolReminder = true;

        Event event = new Event(title, null, null, 0, location, boolRepeat, boolReminder, notes);
        /**dont forget to call scheduleEventNotification(this, event)**/

        /**instead of starting a new activity, simply destroy this one, forcing a return to the previous view
         * (I'm not sure how to do that)**/
        Intent intent = new Intent(this, DayView.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    // TODO: Navigation bar helper code

    public void navOpenHome(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenDayView(){
        Intent intent = new Intent(this, DayView.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenEvent(){
        Intent intent = new Intent(this, AddEvent.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void navOpenInfo(){
        Intent intent = new Intent(this, Infographics.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenReflect(){
        Intent intent = new Intent(this, DailyReflection.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }
}
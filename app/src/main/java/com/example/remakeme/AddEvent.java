package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AddEvent extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;


    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // TODO: Start of Navigation bar code

        Toolbar toolbar = findViewById(R.id.toolbar);
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
                        navOpenHome();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navmenu_dayview:
                        navOpenDayView();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navmenu_newevent:
                        navOpenEvent();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navmenu_infographics:
                        navOpenInfo();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navmenu_reflection:
                        navOpenReflect();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        // TODO: End of Navigation bar code

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        Spinner spinner = findViewById(R.id.spinnerColor);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Red");
        arrayList.add("Orange");
        arrayList.add("Yellow");
        arrayList.add("Green");
        arrayList.add("Blue");
        arrayList.add("Purple");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
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
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repeatList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(arrayAdapter2);
        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
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

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reminderList);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminderSpinner.setAdapter(arrayAdapter3);
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        EditText dateText = findViewById(R.id.editTextDate);
        dateText.setText(date);
    }

    // TODO: Navigation bar helper code

    public void navOpenHome(){
        Intent intent = new Intent(this, com.example.remakeme.MainActivity.class);
        startActivity(intent);
    }

    public void navOpenDayView(){
        Intent intent = new Intent(this, com.example.remakeme.DayView.class);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenEvent(){
        Intent intent = new Intent(this, com.example.remakeme.AddEvent.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void navOpenInfo(){
        Intent intent = new Intent(this, com.example.remakeme.Infographics.class);
        startActivity(intent);
    }

    public void navOpenReflect(){
        Intent intent = new Intent(this, com.example.remakeme.DailyReflection.class);
        startActivity(intent);
    }

}
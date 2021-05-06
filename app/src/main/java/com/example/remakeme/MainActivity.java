package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    private static final String TAG = "CalendarActivity";
    public static String DATE_MESSAGE = "Meme";
    public static String date = "4/30/2021";
    public EventDao eventDao;
    public AppDatabase database;
    public int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        break;
                    case R.id.navmenu_dayView:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent day = new Intent(MainActivity.this, DayView.class);
                        startActivity(day);
                        break;
                    case R.id.navmenu_newEvent:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent event = new Intent(MainActivity.this, AddEvent.class);
                        startActivity(event);
                        break;
                    case R.id.navmenu_infographics:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent info = new Intent(MainActivity.this, Infographics.class);
                        startActivity(info);
                        break;
                    case R.id.navmenu_reflection:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent reflect = new Intent(MainActivity.this, DailyReflection.class);
                        startActivity(reflect);
                        break;
                }
                return true;
            }
        });


        if (start == 0) {
            // Create database and eventDao
        }
        start = 1;

        CalendarView calendarView = (CalendarView) findViewById(R.id.calender);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                month = month + 1;
                date = month + "/" + day + "/" + year;
                Intent intent = DayViewV2.makeIntent(MainActivity.this);
                intent.putExtra(DATE_MESSAGE, MainActivity.date);
                startActivity(intent);
            }
        });
    }

    public void setDate(View view) {
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calender);
        long selectedDate = simpleCalendarView.getDate();
        simpleCalendarView.setDate(selectedDate);
    }
}
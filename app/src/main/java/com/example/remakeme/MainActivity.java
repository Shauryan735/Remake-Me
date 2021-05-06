package com.example.remakeme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

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
                navOpenDayView();
            }
        });

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
        }});


        // TODO: End of Navigation bar code
    }

    public void setDate(View view) {
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calender);
        long selectedDate = simpleCalendarView.getDate();
        simpleCalendarView.setDate(selectedDate);
    }

    // TODO: Navigation bar helper code

    public boolean navOpenHome(){
        return true;
    }

    public void navOpenDayView(){
        Intent intent = DayViewV2.makeIntent(MainActivity.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenEvent(){
        Intent intent = AddEvent.makeIntent(MainActivity.this);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void navOpenInfo(){
        Intent intent = Infographics.makeIntent(MainActivity.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public void navOpenReflect(){
        Intent intent = DailyReflection.makeIntent(MainActivity.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
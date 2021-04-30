package com.example.remakeme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    monthView();
                    break;

                case R.id.navmenu_dayview:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    dayView();
                    break;

                case R.id.navmenu_newevent:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    eventView();
                    break;

                case R.id.navmenu_infographics:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    infographcisView();
                    break;

                case R.id.navmenu_reflection:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    reflectionView();
                    break;
            }
            return true;
        });

        CalendarView calendarView = (CalendarView) findViewById(R.id.calender);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                month = month + 1;
                date = month + "/" + day + "/" + year;
                dayView();
            }
        });
    }

    public void setDate(View view) {
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calender);
        long selectedDate = simpleCalendarView.getDate();
        simpleCalendarView.setDate(selectedDate);
    }

    public void monthView() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void dayView() {
        Intent intent = new Intent(this, DayView.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void eventView() {
        Intent intent = new Intent(this, AddEvent.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void infographcisView() {
        Intent intent = new Intent(this, Infographics.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    public void reflectionView() {
        Intent intent = new Intent(this, DailyReflection.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

}
package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Notes extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    private static final String TAG = "CalendarActivity";
    public static String DATE_MESSAGE = "Meme";
    public static String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

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
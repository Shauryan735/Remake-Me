package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DailyReflection extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reflection);

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
                        Intent day = new Intent(DailyReflection.this, DayView.class);
                        startActivity(day);
                        finish();
                        break;
                    case R.id.navmenu_newEvent:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent event = new Intent(DailyReflection.this, AddEvent.class);
                        startActivity(event);
                        finish();
                        break;
                    case R.id.navmenu_infographics:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent info = new Intent(DailyReflection.this, Infographics.class);
                        startActivity(info);
                        finish();
                        break;
                    case R.id.navmenu_reflection:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

    }
}
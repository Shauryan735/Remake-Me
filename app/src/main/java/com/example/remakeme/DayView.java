package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
//TODO: remove DayView.class
public class DayView extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

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
                        break;
                    case R.id.navmenu_newEvent:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent event = new Intent(DayView.this, AddEvent.class);
                        startActivity(event);
                        finish();
                        break;
                    case R.id.navmenu_infographics:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent info = new Intent(DayView.this, Infographics.class);
                        startActivity(info);
                        finish();
                        break;
                    case R.id.navmenu_reflection:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent reflect = new Intent(DayView.this, DailyReflection.class);
                        startActivity(reflect);
                        finish();
                        break;
                }
                return true;
            }
        });
        // TODO: End of Navigation bar code


        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(date);


        /*add the toolbar and enable upwards navigation*/
        /*Remove when navbar complete*/
//        Toolbar JKsToolbar = (Toolbar) findViewById(R.id.JKsToolbar);
//        setSupportActionBar(JKsToolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void goToCalendar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void menu(View view) {
//        Intent intent = new Intent(this, Menu.class);
//        intent.putExtra(DATE_MESSAGE, date);
//        startActivity(intent);
//    }


    // TODO: Navigation bar helper code
}
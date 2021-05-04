package com.example.remakeme;

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

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(date);

        // TODO: Start of Navigation bar code

        /*Toolbar toolbar = findViewById(R.id.toolbar);
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
            }
        });*/
        // TODO: End of Navigation bar code

        /*add the toolbar and enable upwards navigation*/
        /*Remove when navbar complete*/
        Toolbar JKsToolbar = (Toolbar) findViewById(R.id.JKsToolbar);
        setSupportActionBar(JKsToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
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
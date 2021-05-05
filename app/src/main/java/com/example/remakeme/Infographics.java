package com.example.remakeme;

import android.content.Context;
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

public class Infographics extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infographics);

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
            }
        });
        // TODO: End of Navigation bar code

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);
    }


//    public void menu(View view) {
//        Intent intent = new Intent(this, Menu.class);
//        intent.putExtra(DATE_MESSAGE, date);
//        startActivity(intent);
//    }

    // TODO: Navigation bar helper code

    public void navOpenHome(){
        Intent intent = MainActivity.makeIntent(Infographics.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
        finish();
    }

    public void navOpenDayView(){
        Intent intent = DayView.makeIntent(Infographics.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
        finish();
    }

    public void navOpenEvent(){
        Intent intent = AddEvent.makeIntent(Infographics.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
        finish();
    }

    public boolean navOpenInfo(){
        return true;
    }

    public void navOpenReflect(){
        Intent intent = DailyReflection.makeIntent(Infographics.this);
        intent.putExtra(DATE_MESSAGE, MainActivity.date);
        startActivity(intent);
        finish();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Infographics.class);
    }
}
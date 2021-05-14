package com.example.remakeme;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
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
                    finish();
                    break;
                case R.id.navmenu_dayView:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent day = new Intent(Infographics.this, DayViewV2.class);
                    day.putExtra(DATE_MESSAGE, date);
                    startActivity(day);
                    finish();
                    break;
                case R.id.navmenu_newEvent:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent event = new Intent(Infographics.this, AddEvent.class);
                    startActivity(event);
                    finish();
                    break;
                case R.id.navmenu_infographics:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.navmenu_reflection:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent reflect = new Intent(Infographics.this, DailyReflection.class);
                    startActivity(reflect);
                    finish();
                    break;
            }
            return true;
        });
        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> testData = new ArrayList<>();
        testData.add(new BarEntry(1, 10));
        testData.add(new BarEntry(2, 17));
        testData.add(new BarEntry(3, 23));
        testData.add(new BarEntry(4, 39));
        testData.add(new BarEntry(5, 31));

        BarDataSet barDataSet = new BarDataSet(testData, "Rating of Events");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Test infographics for number of events and ratings");
        barChart.animateY(1000);
    }
}
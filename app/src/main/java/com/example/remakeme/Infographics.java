package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

/**
 * Displays event grades in both bar chart and pie chart formats.
 */
public class Infographics extends AppCompatActivity {

  NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  TabLayout tabLayout;
  ViewPager viewPager;

  String dateMessage = "Meme";
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
          day.putExtra(dateMessage, date);
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
        default:
          drawerLayout.closeDrawer(GravityCompat.START);
          Intent reflect = new Intent(Infographics.this, DailyReflection.class);
          startActivity(reflect);
          finish();
          break;
      }
      return true;
    });
    Intent intent = getIntent();
    date = intent.getStringExtra(dateMessage);

    tabLayout = findViewById(R.id.tabLayout);
    viewPager = findViewById(R.id.pager);

    PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
    pagerAdapter.addFragment(new InfoBarChart(), "Bar Chart");
    pagerAdapter.addFragment(new InfoPieChart(), "Pie Chart");

    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}
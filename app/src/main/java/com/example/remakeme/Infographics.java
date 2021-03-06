package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    pagerAdapter.addFragment(new InfoLineChart(), "Average Grade");
    pagerAdapter.addFragment(new InfoPieChart(), "Event type division");

    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static void shareBitmap(@NonNull Bitmap bitmap, Context context)
  {
    //---Save bitmap to external cache directory---//
    //get cache directory
    File cachePath = new File(context.getExternalCacheDir(), "my_images/");
    cachePath.mkdirs();

    //create png file
    File file = new File(cachePath, "Image_123.png");
    FileOutputStream fileOutputStream;
    try
    {
      fileOutputStream = new FileOutputStream(file);
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
      fileOutputStream.flush();
      fileOutputStream.close();

    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    //---Share File---//
    //get file uri
    Uri myImageFileUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);

    //create a intent
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    intent.putExtra(Intent.EXTRA_STREAM, myImageFileUri);
    intent.setType("image/png");
    context.startActivity(Intent.createChooser(intent, "Share with"));
  }
}
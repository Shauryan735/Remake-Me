package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Math.random;

/**DayReview activity.*/
public class DayReview extends AppCompatActivity {

  String date = "Meme 2.0";
  static final String dateMessage = "Meme";
  NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_day_review);

    Intent intent = getIntent();
    date = intent.getStringExtra(dateMessage);

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
            Intent intent = new Intent(DayReview.this, MainActivity.class);
            startActivity(intent);
            finish();
            break;
          case R.id.navmenu_dayView:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent day = new Intent(DayReview.this, DayViewV2.class);
            day.putExtra(dateMessage, date);
            startActivity(day);
            finish();
            break;
          case R.id.navmenu_newEvent:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent event = new Intent(DayReview.this, AddEvent.class);
            startActivity(event);
            finish();
            break;
          case R.id.navmenu_infographics:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent info = new Intent(DayReview.this, Infographics.class);
            startActivity(info);
            finish();
            break;
          case R.id.navmenu_reflection:
          default:
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent reflect = new Intent(DayReview.this, DailyReflection.class);
            startActivity(reflect);
            finish();
            break;
        }
        return true;
      }
    });

    Button backButton = findViewById(R.id.dayRevBack);
    backButton.setOnClickListener(view -> {
      Intent day = new Intent(DayReview.this, DayViewV2.class);
      day.putExtra(dateMessage, date);
      startActivity(day);
      finish();
    });

    Button reflectButton = findViewById(R.id.dayRevReflect);
    reflectButton.setOnClickListener(view -> {
      Intent day = new Intent(DayReview.this, DailyReflection.class);
      day.putExtra(dateMessage, date);
      startActivity(day);
      finish();
    });

    TextView textView = findViewById(R.id.textView);
    textView.setText(date);

    String[] dateParts = date.split("/");
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, parseInt(dateParts[0]) - 1);
    calendar.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
    calendar.set(Calendar.YEAR, parseInt(dateParts[2]));

    AppDatabase instance = AppDatabase.getInstance(this);
    EventDao eventDao = instance.getEventDao();
    double daysAvgGrade = eventDao.getDaysAverageGrade(Event.getDbFormattedDate(calendar));

    Calendar calendar2 = Calendar.getInstance();
    calendar2.set(Calendar.MONTH, parseInt(dateParts[0]) - 1);
    calendar2.set(Calendar.DAY_OF_MONTH, parseInt(dateParts[1]));
    calendar2.set(Calendar.YEAR, parseInt(dateParts[2]));
    calendar2.add(Calendar.HOUR, -168);

    double weekAvgGrade = eventDao.getAverageGradeInRange(
        Event.getDbFormattedDate(calendar2), Event.getDbFormattedDate(calendar));
    calendar2.add(Calendar.HOUR, -504);
    double monthAvgGrade = eventDao.getAverageGradeInRange(
        Event.getDbFormattedDate(calendar2), Event.getDbFormattedDate(calendar));

    String recommendation = getRecommendation(daysAvgGrade, weekAvgGrade);
    Toast.makeText(this,
        date + " has recommendation " + recommendation, Toast.LENGTH_LONG)
        .show();
  }

  private String getRecommendation(double daysAvgGrade, double weekAvgGrade) {
    String rec = "";
    if (daysAvgGrade >= 90) {
      rec += "Good job today!\n";
    } else if (daysAvgGrade >= 80) {
      rec += "Almost there!\n";
    } else if (daysAvgGrade >= 70) {
      rec += "Room for improvement.\n";
    } else if (daysAvgGrade >= 50) {
      rec += "Try to finish your events!\n";
    } else {
      rec += "Try to stick to your schedule!\n";
    }

    if (weekAvgGrade > daysAvgGrade + 10) {
      rec += "Don't let yourself fall behind!\n";
    } else if (weekAvgGrade < daysAvgGrade - 10) {
      rec += "Nice improvement today! Keep it up!\n";
    } else if ((weekAvgGrade > daysAvgGrade - 10)
        && (weekAvgGrade < daysAvgGrade + 10)
        && (weekAvgGrade < 90)) {
      rec += "Push yourself to improve!\n";
    } else {
      Random rand = new Random();
      int antiCheat = rand.nextInt(100);
      if(antiCheat != 99) {
        rec += "Keep up the good work!\n";
      } else {
        rec += "You've got good grades. I sure hope you're being honest!\n";
      }
    }
    return rec;
  }
}
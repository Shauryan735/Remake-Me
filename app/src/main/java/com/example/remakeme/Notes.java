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

    private static final String TAG = "CalendarActivity";
    public static String DATE_MESSAGE = "Meme";
    public static String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // TODO: End of Navigation bar code

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

    }

    // TODO: Navigation bar helper code


}
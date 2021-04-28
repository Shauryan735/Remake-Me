package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Infographics extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infographics);

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);
    }


    public void menu(View view) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }
}
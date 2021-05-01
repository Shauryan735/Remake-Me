package com.example.remakeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    String DATE_MESSAGE = "Meme";
    String date = "Meme 2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        date = intent.getStringExtra(DATE_MESSAGE);

        Spinner spinner = findViewById(R.id.spinnerColor);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Red");
        arrayList.add("Orange");
        arrayList.add("Yellow");
        arrayList.add("Green");
        arrayList.add("Blue");
        arrayList.add("Purple");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner repeatSpinner = findViewById(R.id.spinnerRepeat);
        ArrayList<String> repeatList = new ArrayList<>();
        repeatList.add("Never");
        repeatList.add("Daily");
        repeatList.add("Weekly");
        repeatList.add("Monthly");
        repeatList.add("Annually");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repeatList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(arrayAdapter2);
        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner reminderSpinner = findViewById(R.id.spinnerReminder);
        ArrayList<String> reminderList = new ArrayList<>();
        reminderList.add("Never");
        reminderList.add("0 minutes before");
        reminderList.add("5 minutes before");
        reminderList.add("15 minutes before");
        reminderList.add("30 minutes before");
        reminderList.add("1 hour before");

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reminderList);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminderSpinner.setAdapter(arrayAdapter3);
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        EditText dateText = findViewById(R.id.editTextDate);
        dateText.setText(date);
    }

    public void menu(View view) {
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra(DATE_MESSAGE, date);
        startActivity(intent);
    }

    private void createEventsOnClick(View view){
        if(getRepeating()){
            int id = genID(getName());
            createRepeatedEvents(id);
        }
        else{ createEvent(); }
    }

    private void createEvent(){
        Event event = new Event();

        event.setEventName(getName());
        event.setEventStart(getStart());
        event.setEventEnd(getEnd());
        event.setGroupColor(getColor());
        event.setGraded(getGraded());
        event.setSendReminders(getSendReminders());
        event.setNote(getNote());
        event.setLocation(getLocation());

        if(event.getSendReminders()){
            event.setRemindOffset(getRemindOffset());
            NotificationPublisher.scheduleEventNotification(this, event);
        }
    }

    private boolean getRepeating(){
        /**dummy method
         * for Noah**/
        return true;
    }
    private int genID(String name){
        return name.hashCode();
    }
    private void createRepeatedEvents(int id){
        /**dummy method
         * for each of n events, createEvent()**/
    }
    private String getName(){
        /**dummy method
         * for Noah**/
        return "";
    }
    private Calendar getStart(){
        /**dummy method
         * for Noah**/
        return Calendar.getInstance();
    }
    private Calendar getEnd(){
        /**dummy method
         * for Noah**/
        return Calendar.getInstance();
    }
    private int getColor(){
        /**dummy method
         * for Noah**/
        return -1;
    }
    private boolean getGraded(){
        /**dummy method
         * for Noah**/
        return true;
    }
    private boolean getSendReminders(){
        /**dummy method
         * for Noah**/
        return true;
    }
    private String getNote(){
        /**dummy method
         * for Noah**/
        return "";
    }
    private String getLocation(){
        /**dummy method
         * for Noah**/
        return "";
    }
    private long getRemindOffset() {
        /**dummy method
         * for Noah**/
        return -1;
    }
}
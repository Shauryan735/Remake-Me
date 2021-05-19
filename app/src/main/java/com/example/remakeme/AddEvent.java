package com.example.remakeme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
// import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
// import android.widget.Toast;
// import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Activity to create events for the calendar.
 */
public class AddEvent extends AppCompatActivity {

  private EventDao eventDao;

  private NavigationView nav;
  ActionBarDrawerToggle toggle;
  DrawerLayout drawerLayout;

  private final String dateMessage = "Meme";
  private String date = "Meme 2.0";
  private final String eventMessage = "event_key";

  private String groupColor = "Red";
  private String repeat = "Never";
  private String reminder = "Never";
  private String startHour = "";
  private String startMinute = "";
  private String endHour = "";
  private String endMinute = "";
  private Boolean editing = false;
  private long eventId = 0;
  private int grade = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_event);

    Intent intent = getIntent();
    date = intent.getStringExtra(dateMessage);

    Button submitButton = findViewById(R.id.eventViewEditButton);

    try {
      eventId = intent.getLongExtra(eventMessage, 0);
      if (eventId != 0) {
        editing = true;
        submitButton.setText(R.string.editEvent);
      }
    } catch (Exception ignored) {
      editing = false;
    }

    EditText editDate = findViewById(R.id.editTextDate);
    editDate.setText(date);

    AppDatabase instance = AppDatabase.getInstance(this);
    eventDao = instance.getEventDao();
    // eventDao.clearAll(); // Uncomment to clear database

    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nav = findViewById(R.id.nav);
    drawerLayout = findViewById(R.id.drawer);

    toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    nav.setNavigationItemSelectedListener(item -> {

      int itemId = item.getItemId();
      if (itemId == R.id.navmenu_home) {
        drawerLayout.closeDrawer(GravityCompat.START);
        finish();
      } else if (itemId == R.id.navmenu_dayView) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent day = new Intent(AddEvent.this, DayViewV2.class);
        day.putExtra(dateMessage, date);
        startActivity(day);
        finish();
      } else if (itemId == R.id.navmenu_newEvent) {
        drawerLayout.closeDrawer(GravityCompat.START);
      } else if (itemId == R.id.navmenu_infographics) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent info = new Intent(AddEvent.this, Infographics.class);
        startActivity(info);
        finish();
      } else if (itemId == R.id.navmenu_reflection) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent reflect = new Intent(AddEvent.this, DailyReflection.class);
        startActivity(reflect);
        finish();
      }
      return true;
    });

    // TODO: End of Navigation bar code


    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("Red");
    arrayList.add("Orange");
    arrayList.add("Yellow");
    arrayList.add("Green");
    arrayList.add("Blue");
    arrayList.add("Purple");
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, arrayList);
    arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
    Spinner spinner = findViewById(R.id.spinnerColor);
    spinner.setAdapter(arrayAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        groupColor = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    ArrayList<String> repeatList = new ArrayList<>();
    repeatList.add("Never");
    repeatList.add("Daily");
    repeatList.add("Weekly");
    repeatList.add("Monthly");
    repeatList.add("Annually");
    ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, repeatList);
    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    Spinner repeatSpinner = findViewById(R.id.spinnerRepeat);
    repeatSpinner.setAdapter(arrayAdapter2);
    repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        repeat = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    ArrayList<String> reminderList = new ArrayList<>();
    reminderList.add("Never");
    reminderList.add("0 minutes before");
    reminderList.add("5 minutes before");
    reminderList.add("15 minutes before");
    reminderList.add("30 minutes before");
    reminderList.add("1 hour before");

    ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, reminderList);
    arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    Spinner reminderSpinner = findViewById(R.id.spinnerReminder);

    reminderSpinner.setAdapter(arrayAdapter3);
    reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        reminder = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });


    ArrayList<String> startHourList = new ArrayList<>();
    startHourList.add("12");
    startHourList.add("01");
    startHourList.add("02");
    startHourList.add("03");
    startHourList.add("04");
    startHourList.add("05");
    startHourList.add("06");
    startHourList.add("07");
    startHourList.add("08");
    startHourList.add("09");
    startHourList.add("10");
    startHourList.add("11");

    ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, startHourList);
    arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    Spinner startHourSpinner = findViewById(R.id.spinnerStartHour);

    startHourSpinner.setAdapter(arrayAdapter4);
    startHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        startHour = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });


    ArrayList<String> startMinuteList = new ArrayList<>();
    startMinuteList.add("00");
    startMinuteList.add("15");
    startMinuteList.add("30");
    startMinuteList.add("45");

    ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, startMinuteList);
    arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Spinner startMinuteSpinner = findViewById(R.id.spinnerStartMinute);
    startMinuteSpinner.setAdapter(arrayAdapter5);
    startMinuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        startMinute = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    ArrayList<String> endHourList = new ArrayList<>();
    endHourList.add("12");
    endHourList.add("01");
    endHourList.add("02");
    endHourList.add("03");
    endHourList.add("04");
    endHourList.add("05");
    endHourList.add("06");
    endHourList.add("07");
    endHourList.add("08");
    endHourList.add("09");
    endHourList.add("10");
    endHourList.add("11");

    ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, endHourList);
    arrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    Spinner endHourSpinner = findViewById(R.id.spinnerEndHour);

    endHourSpinner.setAdapter(arrayAdapter6);
    endHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        endHour = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    ArrayList<String> endMinuteList = new ArrayList<>();
    endMinuteList.add("00");
    endMinuteList.add("15");
    endMinuteList.add("30");
    endMinuteList.add("45");

    ArrayAdapter<String> arrayAdapter7 = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, endMinuteList);
    arrayAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    Spinner endMinuteSpinner = findViewById(R.id.spinnerEndMinute);

    endMinuteSpinner.setAdapter(arrayAdapter7);
    endMinuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        endMinute = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });


    long[] events = {eventId};

    if (editing) {
      EditText editName = findViewById(R.id.editTextName);
      submitButton.setText(R.string.editEvent);


      List<Event> eventList = eventDao.getById(events);
      Event editEvent = eventList.get(0);

      // Switch to below for basic testing

      /* Calendar start1 = Calendar.getInstance();
            Calendar end1 = Calendar.getInstance();
            Event editEvent = new Event("Cool Event", start1, end1, 0xFFFFA500,
            "San Luis Obispo", false, 0, false, "Really cool note");
            */

      editName.setText(editEvent.getEventName());

      Calendar gotDate = editEvent.getEventStart();
      Date c = gotDate.getTime();
      SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
      String stringDate = df.format(c);
      editDate.setText(stringDate);

      String oldStartTime = editEvent.getFormattedStartTime();
      String[] oldStartArray = oldStartTime.split(":");
      startHourSpinner.setSelection(arrayAdapter4.getPosition(oldStartArray[0]));
      startMinuteSpinner.setSelection(arrayAdapter5.getPosition(oldStartArray[1]));

      String oldEndTime = editEvent.getFormattedEndTime();
      String[] oldEndArray = oldEndTime.split(":");
      endHourSpinner.setSelection(arrayAdapter6.getPosition(oldEndArray[0]));
      endMinuteSpinner.setSelection(arrayAdapter7.getPosition(oldEndArray[1]));

      String hexColor;
      switch (editEvent.getGroupColor()) {
        case 0xFFFFA500:
          hexColor = "Orange";
          break;
        case 0xFFFFFF00:
          hexColor = "Yellow";
          break;
        case 0xFF008000:
          hexColor = "Green";
          break;
        case 0xFF0000FF:
          hexColor = "Blue";
          break;
        case 0xFF800080:
          hexColor = "Purple";
          break;
        case 0xFFFF0000:
        default:
          hexColor = "Red";
          break;
      }
      spinner.setSelection(arrayAdapter.getPosition(hexColor));

      EditText editLocation = findViewById(R.id.editTextLocation);
      editLocation.setText(editEvent.getLocation());

      repeatSpinner.setSelection(editEvent.getRepeatOffset());
      reminderSpinner.setSelection(0);

      EditText editNotes = findViewById(R.id.editTextNotes);
      editNotes.setText(editEvent.getNote());

      Switch gradedSwitch = findViewById(R.id.gradedSwitch);
      gradedSwitch.setChecked(editEvent.isGraded());

      grade = editEvent.getGrade();
    }
  }

  /**
   * Sends the submitted data to the database.
   * If editing an event, will instead edit the event.
   */
  public void submit(View view) {
    boolean boolRepeat = false;
    boolean boolReminder = false;
    int repeatOffset = 0;
    int color;

    EditText editTitle = findViewById(R.id.editTextName);
    String title;
    try {
      title = editTitle.getText().toString();
      if (title.equals("")) {
        title = "New Event";
      }
    } catch (Exception e) {
      title = "New Event";
    }

    EditText editLocation = findViewById(R.id.editTextLocation);
    String location;
    try {
      location = editLocation.getText().toString();
    } catch (Exception e) {
      location = "None";
    }

    EditText editNotes = findViewById(R.id.editTextNotes);
    String notes;
    try {
      notes = editNotes.getText().toString();
    } catch (Exception e) {
      notes = "None";
    }

    final int hour1 = Integer.parseInt(startHour);
    final int minute1 = Integer.parseInt(startMinute);
    final int hour2 = Integer.parseInt(endHour);
    final int minute2 = Integer.parseInt(endMinute);

    Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();

    EditText editDate = findViewById(R.id.editTextDate);
    date = editDate.getText().toString();
    String[] numbers = date.split("/");
    int year = Integer.parseInt(numbers[2]);
    int month = Integer.parseInt(numbers[0]) - 1;
    int day = Integer.parseInt(numbers[1]);

    startCalendar.set(year, month, day, hour1, minute1);
    endCalendar.set(year, month, day, hour2, minute2);


    if (!(repeat.equals("Never"))) {
      boolRepeat = true;
      switch (repeat) {
        case "Daily":
          repeatOffset = 1;
          break;
        case "Weekly":
          repeatOffset = 2;
          break;
        case "Biweekly":
          repeatOffset = 3;
          break;
        case "Monthly":
          repeatOffset = 4;
          break;
        default:
          repeatOffset = 0;
          break;
      }
    }

    if (!(reminder.equals("Never"))) {
      boolReminder = true;
    }

    switch (groupColor) {
      case "Orange":
        color = 0xFFFFA500;
        break;
      case "Yellow":
        color = 0xFFFFFF00;
        break;
      case "Green":
        color = 0xFF008000;
        break;
      case "Blue":
        color = 0xFF0000FF;
        break;
      case "Purple":
        color = 0xFF800080;
        break;
      case "Red":
      default:
        color = 0xFFFF0000;
        break;
    }

    Switch gradedSwitch = findViewById(R.id.gradedSwitch);
    Boolean graded = gradedSwitch.isChecked();

    Event event = new Event(title, startCalendar, endCalendar, color,
            location, boolRepeat, repeatOffset, boolReminder, notes, graded, grade);
    /* Toast.makeText(getApplicationContext(),
            event.getEventStart().get(Calendar.MONTH) + "/" +
            event.getEventStart().get(Calendar.DATE), Toast.LENGTH_LONG)
            .show(); */

    if (!editing) {
      eventId = eventDao.insert(event);
      if (boolRepeat) {
        repeat(eventId);
      }
      event.setId(eventId);
      if (event.getSendReminders()) {
        NotificationPublisher.scheduleEventNotification(this, event);
      }

      Intent intent = new Intent(this, DayViewV2.class);
      intent.putExtra(dateMessage, date);
      startActivity(intent);
    } else {
      event.setId(eventId);
      eventDao.updateEvent(event);
      Intent intent = new Intent(this, EventView.class);
      intent.putExtra(eventMessage, eventId);
      startActivity(intent);
    }
    finish();
  }

  // TODO: Navigation bar helper code

  public static Intent makeIntent(Context context) {
    return new Intent(context, AddEvent.class);
  }

  private void incrementDayOfYear(Event event, int by) {
    Calendar start = event.getEventStart();
    Calendar end = event.getEventEnd();
    start.add(Calendar.DAY_OF_YEAR, by);
    end.add(Calendar.DAY_OF_YEAR, by);
    event.setEventStart(start);
    event.setEventEnd(end);
  }

  /**
   * Adds multiple events to the database to have events repeat.
   */
  public void repeat(long baseId) {
    long[] ids = {baseId};
    Event event = eventDao.getById(ids).get(0);
    event.setId(0);
    event.setBaseId(baseId);                    //repeat offset is being treated as follows:
    int repeatOffset = event.getRepeatOffset(); //Daily:   1   |Weekly:   2
    int i = 0;                                  //BiWeekly:3   |Monthly:  4
    if (repeatOffset == 1) {
      while (i < 365) {
        incrementDayOfYear(event, 1);
        eventDao.insert(event);
        i += 1;
      }
    } else if (repeatOffset == 2) {
      while (i < 365) {
        incrementDayOfYear(event, 7);
        eventDao.insert(event);
        i += 7;
      }
    } else if (repeatOffset == 3) {
      while (i < 365) {
        incrementDayOfYear(event, 14);
        eventDao.insert(event);
        i += 14;
      }
    } else if (repeatOffset == 4) {
      while (i < 12) {
        Calendar start = event.getEventStart();
        Calendar end = event.getEventEnd();
        start.add(Calendar.MONTH, 1);
        end.add(Calendar.MONTH, 1);
        event.setEventStart(start);
        event.setEventEnd(end);

        eventDao.insert(event);
        i += 1;
      }
    } else if (repeatOffset == 5) {
      Calendar start = event.getEventStart();
      Calendar end = event.getEventEnd();
      start.add(Calendar.YEAR, 1);
      end.add(Calendar.YEAR, 1);
      event.setEventStart(start);
      event.setEventEnd(end);

      eventDao.insert(event);
    }
  }
}
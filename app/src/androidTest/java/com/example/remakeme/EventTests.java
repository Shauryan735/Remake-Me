package com.example.remakeme;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class EventTests {

  @Test
  public void createEvent() {
    Event event1 = new Event();
    assertNotNull(event1.getEventStart());
    assertNotNull(event1.getEventEnd());
    assertNotNull(event1.getRemindTime());

    Event event2 = new Event("Cool Event", Calendar.getInstance(), Calendar.getInstance(),
            0xFFFFFFFF, "Cool Place", false, 0,
            false, "Note", true, 0);
    assertNotNull(event1.getEventStart());
    assertNotNull(event1.getEventEnd());
    assertEquals(event2.getGroupColor(), 0xFFFFFFFF);
    assertEquals(event2.getLocation(), "Cool Place");
    assertEquals(event2.getRepeat(), false);
    assertEquals(event2.getRepeatOffset(), 0);
    assertEquals(event2.getSendReminders(), false);
    assertEquals(event2.getNote(), "Note");
    assertTrue(event2.isGraded());
  }

  @Test
  public void eventFunctions() {
    int year = 2021;
    int month = 9;
    int day = 20;
    int hour = 12;
    int minute1 = 30;
    int minute2 = 45;
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    calendar1.set(year, month - 1, day, hour, minute1);
    calendar2.set(year, month - 1, day, hour, minute2);
    Event event = new Event();
    event.setEventStart(calendar1);
    event.setEventEnd(calendar2);
    assertEquals(event.getFormattedTime(), "12:30 - 12:45");
    assertEquals(event.getFormattedStartTime(), "12:30");
    assertEquals(event.getFormattedEndTime(), "12:45");

    int color = 0xFF0000FF;
    event.setGroupColor(color);

    assertEquals(event.getGroupColoredBox(), R.drawable.blue_box);
    assertEquals(event.getGroupColoredOutline(), R.drawable.blue_boarder);
  }

  /*
  Navigation Checklist:

   */
}

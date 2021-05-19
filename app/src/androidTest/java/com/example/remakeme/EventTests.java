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
            false, "Note", true);
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
}

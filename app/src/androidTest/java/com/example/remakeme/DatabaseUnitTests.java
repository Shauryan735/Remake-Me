package com.example.remakeme;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseUnitTests {
    private EventDao eventDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context;
        context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        eventDao = db.getEventDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    /*
     * Tests basic insert and get all statements
     */
    @Test
    public void test_writeReadDelete() throws Exception {
        Event event = new Event();
        event.setEventName("test_event");
        eventDao.insertAll(event);

        List<Event> all = eventDao.getAll();
        assertEquals((all.get(0)).getEventName(), "test_event");

        event = all.get(0);
        int numDeleted = eventDao.deleteAll(event);
        assertEquals(1, numDeleted);
        List<Event> newByName = eventDao.getAll();
        assertEquals(0, newByName.size());
    }

    /*
     * Tests by days query with one day in list
     * Then tests by days query with multiple days in list
     */
    @Test
    public void test_byDaysQuery() throws Exception{
        Event event = new Event();
        event.setId(0);
        event.setEventName("1st Event");
        event.setEventStart(Calendar.getInstance());
        eventDao.insertAll(event);

        event.setEventName("2nd Event");
        event.setEventStart(Calendar.getInstance());
        eventDao.insertAll(event);

        List<Event> months = eventDao.getByMonth("04");
        assertEquals(2, months.size());
        assertEquals("1st Event", months.get(0).getEventName());
        assertEquals("2nd Event", months.get(1).getEventName());

        List<Event> no_months = eventDao.getByMonth("05");
        assertEquals(0, no_months.size());
        eventDao.deleteAll(months.get(0), months.get(1));
    }

    @Test
    public void test_update() throws Exception{
        Event event = new Event();
        event.setEventName("before");
        eventDao.insertAll(event);

        List<Event> months = eventDao.getAll();
        assertEquals(1, months.size());
        assertEquals("before", months.get(0).getEventName());

        months.get(0).setEventName("after");
        eventDao.updateEvent(months.get(0));

        months = eventDao.getAll();
        assertEquals(1, months.size());
        assertEquals("after", months.get(0).getEventName());
        eventDao.delete(months.get(0));
    }

    @Test
    public void test_getByName() throws Exception{
        Event event = new Event();
        event.setEventName("name_test");
        eventDao.insertAll(event);

        String[] names = {"name_test"};
        List<Event> events = eventDao.getByName(names);
        assertEquals(1, events.size());
        assertEquals("name_test", events.get(0).getEventName());
        eventDao.delete(events.get(0));
    }

    @Test
    public void test_insertReturnsLong() throws Exception{
        Event event = new Event();
        event.setEventName("other name");
        long id;
        id = eventDao.insert(event);
        assertEquals(1, id);
    }

    @Test
    public void test_getNonLiveByDay() throws Exception{
        Event event = new Event();
        event.setEventName("other name");
        Calendar calendar = Calendar.getInstance();
        event.setEventStart(calendar);
        long id;
        id = eventDao.insert(event);
        assertEquals(1, id);

        List<String> dateTimes = eventDao.getDateTimes();
        //assertEquals("", dateTimes.get(0));
        List<Event> eventList = eventDao.getNonLiveByDay("2021-05-08");
        assertEquals("other name", eventList.get(0).getEventName());

    }

}

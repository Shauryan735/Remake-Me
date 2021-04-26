package com.example.remakeme;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void closeDb() {
        db.close();
    }

    /*
     * Tests basic insert and get all statements
     */
    @Test
    public void test_writeUserAndReadInList() throws Exception {
        Event event = new Event();
        event.setEventName("test_event");

        eventDao.insertAll(event);
        List<Event> byName = eventDao.getAll();
        assertEquals((byName.get(0)).getEventName(), "test_event");
        event = byName.get(0);
        int numDeleted = eventDao.deleteAll(event);
        assertEquals(1, numDeleted);

        //List<Event> newByName = eventDao.getAll();

        //assertEquals(0, byName.size());
    }

    /*
     * Tests delete statement
     */
    @Test
    public void test_deleteStatement() throws Exception{
        Event event = new Event();
        event.setEventName("test_event");
        eventDao.insertAll(event);
        List<Event> byName = eventDao.getAll();

        //finish implementation
        assertEquals(2, 2);
    }

    /*
     * Tests by days query with one day in list
     * Then tests by days query with multiple days in list
     */
    @Test
    public void test_byDaysQuery() throws Exception{
        Event event = new Event();
        event.setEventName("test_event");
        eventDao.insertAll(event);
        event.setEventName("test_event2");
        eventDao.insertAll(event);
        List<Event> byName = eventDao.getAll();

        Event event1 = byName.get(0);
        Event event2 = byName.get(1);

        //finish implementation
        assertEquals(event1.getEventName(), "test_event");
        assertEquals(event2.getEventName(), "test_event2");
    }
}

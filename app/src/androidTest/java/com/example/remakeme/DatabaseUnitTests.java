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

    @Test
    public void writeUserAndReadInList() throws Exception {
        Event event = new Event();
        event.setEventName("test_event");
        eventDao.insertAll(event);
        List<Event> byName = eventDao.getAll();
        assertEquals((byName.get(0)).getEventName(), "test_event");
    }
}

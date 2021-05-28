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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ReflectionTableTests {
  private ReflectionDao reflectionDao;
  private AppDatabase db;

  @Before
  public void createDb() {
    Context context;
    context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    reflectionDao = db.getReflectionDao();
  }

  @After
  public void closeDb() throws IOException {
    db.close();
  }

  @Test
  public void test_basicFunctionality() {
    Reflection reflection = new Reflection();
    reflection.setReflection("reflecting... ");
    long rId = reflectionDao.insert(reflection);
    assertEquals(1, rId);

    List<Reflection> result = reflectionDao.getAll();
    assertEquals(1, result.size());
    assertEquals("reflecting... ", result.get(0).getReflection());
  }

  @Test
  public void test_byDate() {
    Reflection reflection = new Reflection();
    reflection.setReflection("reflecting... ");
    reflection.setReflectionDate("2021-05-26");
    long rId = reflectionDao.insert(reflection);

    List<Reflection> result = reflectionDao.getByDate("2021-05-26");
    assertEquals(1, result.size());
    assertEquals("reflecting... ", result.get(0).getReflection());
  }
}

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
public class ProjectTableTests {
  private ProjectDao projectDao;
  private AppDatabase db;

  @Before
  public void createDb() {
    Context context;
    context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    projectDao = db.getProjectDao();
  }

  @After
  public void closeDb() throws IOException {
    db.close();
  }

  @Test
  public void testWorkingDB() throws Exception {
    Project project = new Project();
    project.setProjectName("test_project");

    long projectRetId = projectDao.insert(project);

    assertEquals(1, projectRetId);

    List<Project> dbList = projectDao.getAll();
    assertEquals("test_project", dbList.get(0).getProjectName());
  }

}

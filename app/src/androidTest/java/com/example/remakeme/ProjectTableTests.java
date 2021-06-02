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
import java.util.ArrayList;
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

  @Test
  public void testListToStringConversion() throws Exception {
    Project project = new Project();

    project.addEventID(1);
    project.addEventID(2);
    project.addEventID(3);

    projectDao.insert(project);

    List<Project> projects = projectDao.getAll();

    assertNotEquals(0, projects.get(0).getEventIds().size());
  }

  @Test
  public void testGetProjectByEventId() throws Exception {
    Project project = new Project();
    project.setProjectName("test_project");

    project.addEventID(1);
    project.addEventID(2);
    project.addEventID(3);

    projectDao.insert(project);
    List<Project> projects = projectDao.getProjectsByEventId(1);

    ArrayList<Long> events = projects.get(0).getEventIds();

    assertEquals(new Long(1), events.get(0));

    project.addEventID(4);
    projectDao.insert(project);
    projects = projectDao.getProjectsByEventId(4);
    events = projects.get(0).getEventIds();
    assertEquals(new Long(4), events.get(3));
  }

}

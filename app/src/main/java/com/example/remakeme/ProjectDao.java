package com.example.remakeme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDao {

  @Query("SELECT * FROM projects")
  List<Project> getAll();

  @Query("SELECT * FROM projects WHERE event_ids like '%' || :id || ', ' || '%'")
  List<Project> getProjectsByEventId(long id);

  @Update
  int update(Project project);

  @Delete
  int delete(Project project);

  @Insert
  long insert(Project project);
}

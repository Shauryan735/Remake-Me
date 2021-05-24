package com.example.remakeme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProjectDao {

  @Query("SELECT * FROM projects")
  List<Project> getAll();

  @Delete
  int delete(Project project);

  @Insert
  long insert(Project project);
}

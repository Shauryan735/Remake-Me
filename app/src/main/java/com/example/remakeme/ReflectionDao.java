package com.example.remakeme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReflectionDao {
  @Query("SELECT * FROM reflections")
  List<Reflection> getAll();

  @Query("SELECT * FROM reflections WHERE reflection_date = :rd")
  List<Reflection> getByDate(String rd);

  @Update
  int update(Reflection reflection);

  @Delete
  int delete(Reflection reflection);

  @Insert
  long insert(Reflection reflection);
}

package com.example.remakeme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reflections")
public class Reflection {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "reflection_id")
  private long reflectionId;

  private String reflection;
  @ColumnInfo(name = "reflection_date")
  private String reflectionDate;

  public Reflection() {
  }

  public long getReflectionId() {
    return reflectionId;
  }

  public void setReflectionId(long reflectionId) {
    this.reflectionId = reflectionId;
  }

  public String getReflection() {
    return reflection;
  }

  public void setReflection(String reflection) {
    this.reflection = reflection;
  }

  public String getReflectionDate() {
    return reflectionDate;
  }

  public void setReflectionDate(String reflectionDate) {
    this.reflectionDate = reflectionDate;
  }
}

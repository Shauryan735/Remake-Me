package com.example.remakeme;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "projects")
public class Project {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "project_id")
  private Long _pId;
  private String projectName;

  @NonNull
  @ColumnInfo(name = "event_ids")
  private ArrayList<Long> eventIds;

  public Project() {
    eventIds = new ArrayList<Long>();
  }

  public Long get_pId() {
    return _pId;
  }

  public void set_pId(Long pId) {
    this._pId = pId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  @NonNull
  public ArrayList<Long> getEventIds() {
    return eventIds;
  }

  public void setEventIds(@NonNull ArrayList<Long> eventIds) {
    this.eventIds = eventIds;
  }

  public void addEventID(long eventId) {
    this.eventIds.add(new Long(eventId));
  }
}

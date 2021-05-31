package com.example.remakeme;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Dao to get events from DB
 */
@Dao
public interface EventDao {

  @Query("SELECT * FROM events")
  List<Event> getAll();

  @Query("SELECT * FROM events WHERE event_id IN (:event_ids)")
  List<Event> getById(long[] event_ids);

  @Query("SELECT "
          + "* "
          + "FROM "
          + "events "
          + "WHERE date(eventStart) IN (date(:event_dates))")
  List<Event> getByDates(String[] event_dates);

  @Query("SELECT * FROM events WHERE eventName IN (:event_names)")
  List<Event> getByName(String[] event_names);

  //ADD: get events for a month
  @Query("SELECT "
          + "* "
          + "FROM "
          + "events "
          + "WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 6, 2) = :month")
  List<Event> getByMonth(String month);

  //TODO: GET EVENT BY DATE RANGE AND GRADE RANGE
  @Query("SELECT * FROM events WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) >= :minDate and "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) <= :maxDate and "
          + "grade >= :minGrade and "
          + "grade <= :maxGrade")
  List<Event> getByDateGrade(String minDate, String maxDate, int minGrade, int maxGrade);

  //TODO: GET EVENT BY DATE RANGE AND COLOR
  @Query("SELECT * FROM events WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) >= :minDate and "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) <= :maxDate and "
          + "groupColor = :color")
  List<Event> getByDateColor(String minDate, String maxDate, int color);

  // How to use
  // https://stackoverflow.com/questions/54866247/android-assign-livedata-to-listview
  //"YYYY-MM-DD"
  //"2020-03-28"
  @Query("SELECT "
          + "* "
          + "FROM "
          + "events "
          + "WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) = :day")
  LiveData<List<Event>> getByDay(String day);

  @Query("SELECT "
          + "* "
          + "FROM "
          + "events "
          + "WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) = :day")
  List<Event> getNonLiveByDay(String day);

  @Query("SELECT datetime(eventStart/1000, 'unixepoch', 'localtime') FROM events")
  List<String> getDateTimes();

  @Query("SELECT * FROM events "
          + "INNER JOIN projects "
          + "on projects.event_ids like '%' || events.event_id || ', ' || '%'"
          + "WHERE projects.project_id = :pid")
  List<Event> getProjectEvents(long pid);

  @Query("SELECT avg(grade) FROM events "
          + "where substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) = :day and "
          + "graded = 1")
  double getDaysAverageGrade(String day);

  @Query("SELECT avg(grade) FROM events WHERE "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) >= :minDate and "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) <= :maxDate and "
          + "graded = 1")
  double getAverageGradeInRange(String minDate, String maxDate);

  @Query("SELECT avg_grade from ("
          + "SELECT substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) as date, "
          + "avg(grade) as avg_grade "
          + "FROM events "
          + "WHERE graded = 1 "
          + "GROUP BY date "
          + "ORDER BY date)")
  List<Double> getAverageGrades();

  @Query("SELECT avg_grade from ("
          + "SELECT substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) as date, "
          + "avg(grade) as avg_grade "
          + "FROM events "
          + "WHERE graded = 1 and "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) >= :minDate and "
          + "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) <= :maxDate "
          + "GROUP BY date "
          + "ORDER BY date)")
  List<Double> getAverageGradesInRange(String minDate, String maxDate);

  @Query("DELETE FROM events")
  void clearAll();

  @Update
  int updateEvent(Event event);

  @Insert
  long insert(Event event);

  @Insert
  void insertAll(Event... events);

  @Delete
  void delete(Event event);

  @Delete
  int deleteAll(Event... events);
}

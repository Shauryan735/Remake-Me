package com.example.remakeme;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM events")
    List<Event> getAll();

    @Query("SELECT * FROM events WHERE event_id IN (:event_ids)")
    List<Event> getById(long[] event_ids);

    @Query("SELECT " +
            "* " +
            "FROM " +
            "events " +
            "WHERE date(eventStart) IN (date(:event_dates))")
    List<Event> getByDates(String[] event_dates);

    @Query("SELECT * FROM events WHERE eventName IN (:event_names)")
    List<Event> getByName(String[] event_names);

    //ADD: get events for a month
    @Query("SELECT " +
            "* " +
            "FROM " +
            "events " +
            "WHERE " +
            "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 6, 2) = :month")
    List<Event> getByMonth(String month);

    @Query("SELECT " +
            "* " +
            "FROM " +
            "events " +
            "WHERE " +
            "substr(datetime(eventStart/1000, 'unixepoch', 'localtime'), 1, 10) = :day")
    LiveData<List<Event>> getByDay(String day);

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

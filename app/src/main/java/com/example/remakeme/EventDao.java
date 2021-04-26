package com.example.remakeme;

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
    List<Event> getById(int[] event_ids);

    @Query("SELECT * FROM events WHERE date(eventStart) IN (date(:event_days))")
    List<Event> getByDays(Calendar[] event_days);

    @Query("SELECT * FROM events WHERE eventName IN (:event_names)")
    List<Event> getByName(String[] event_names);

    //ADD: get events for a month
    @Query("SELECT * FROM events WHERE substr(date(eventStart), 6, 2) = :month")
    List<Event> getByMonth(String month);

    @Update
    int updateEvent(Event event);

    @Insert
    void insertAll(Event... events);

    @Delete
    void delete(Event event);
}

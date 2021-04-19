package com.example.remakeme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM events")
    List<Event> getAll();

    @Query("SELECT * FROM events WHERE event_id IN (:event_ids)")
    List<Event> getById(int[] event_ids);

    @Query("SELECT * FROM events WHERE eventDate IN (:event_days)")
    List<Event> getByDays(LocalDate[] event_days);

    @Insert
    void insertAll(Event... events);

    @Delete
    void delete(Event event);
}

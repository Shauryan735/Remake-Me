package com.example.remakeme;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
@TypeConverters({CalendarConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao getEventDao();
}

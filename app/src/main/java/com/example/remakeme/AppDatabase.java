package com.example.remakeme;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Database to store event data.
 */
@Database(entities = {Event.class, Project.class},
        version = 3, exportSchema = true)
@TypeConverters({CalendarConverter.class, ArraySqlConverter.class})
public abstract class AppDatabase extends RoomDatabase {
  private static final String DB_NAME = "calendar_db";
  private static AppDatabase instance;

  /**
   * TODO: Add function description.
   */
  public static synchronized AppDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build();
    }
    return instance;
  }

  public abstract EventDao getEventDao();
  public abstract ProjectDao getProjectDao();
}

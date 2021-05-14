package com.example.remakeme;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Event.class}, version = 3, exportSchema = false)
@TypeConverters({CalendarConverter.class})
public abstract class AppDatabase extends RoomDatabase {
  private static final String DB_NAME = "calendar_db";
  private static AppDatabase instance;

  public static synchronized AppDatabase getInstance(Context context){
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build();
    }
    return instance;
  }

  public abstract EventDao getEventDao();
}

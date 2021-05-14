package com.example.remakeme;

import androidx.room.TypeConverter;
import java.util.Calendar;

public class CalendarConverter {

  @TypeConverter
  public static Calendar toCalendar(Long longCalendarTime){
    Calendar newCalendar = null;
    if(longCalendarTime != null) {
      newCalendar = Calendar.getInstance();
      newCalendar.setTimeInMillis(longCalendarTime);
    }
    return longCalendarTime == null ? null : newCalendar;
  }

  @TypeConverter
  public static Long toLong(Calendar calendar){
    return calendar == null ? null : calendar.getTimeInMillis();
  }
}

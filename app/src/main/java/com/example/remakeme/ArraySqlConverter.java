package com.example.remakeme;

import androidx.room.TypeConverter;
import java.util.ArrayList;

/**
 * Converts arrays to strings and strings to arrays.
 */
public class ArraySqlConverter {
  /**
   * Converts non null strings to ArrayList of Long.
   */
  @TypeConverter
  public ArrayList<Long> toLongArrayList(String listString) {
    ArrayList<Long> events = new ArrayList<Long>();
    String modified = listString.replaceAll("\\[|\\]|\\s| ", "");
    String[] listStringSplit = modified.split(",");

    for (int i = 0; i < listStringSplit.length; i++) {
      try {
        events.add(Long.parseLong(listStringSplit[i]));
      } catch (NumberFormatException nfe) {
        //NOTE: write something here if you need to recover from formatting errors
      }
    }
    return events;
  }

  @TypeConverter
  public String toArrayString(ArrayList<Long> eventsArray) {
    String result = eventsArray.toString();
    return result;
  }
}

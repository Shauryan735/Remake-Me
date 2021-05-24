package com.example.remakeme;

import android.annotation.SuppressLint;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Calendar;
import java.util.Locale;
/*
 * Documentation for Calendar library:
 * https://developer.android.com/reference/java/util/Calendar
 *
 */

/**
 * Data type class to store calendar events.
 */
@Entity(tableName = "events")
public class Event {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "event_id")
  private long id;

  private String eventName;
  private Calendar eventStart;
  private Calendar eventEnd;

  private Boolean sendReminders;
  private Calendar remindTime;

  private Boolean repeat;
  private int repeatOffset = 0;

  private String note;
  private int groupColor;
  private String location;
  private long baseId;
  private long remindOffset;
  private String groupName;

  private String reviewNote;

  private boolean graded;
  private int grade;

  /**
  * Event default constructor method.
  */
  public Event() {
    this.eventStart = Calendar.getInstance();
    this.eventEnd = Calendar.getInstance();
    this.remindTime = Calendar.getInstance();
  }

  /**
   * Constructor method used by the AddEvent class.
   */
  @Ignore
  public Event(String eventName, Calendar eventStart, Calendar eventEnd,
               int groupColor, String location, Boolean repeat, int repeatOffset,
               Boolean sendReminders, String note, Boolean graded, int grade) {
    this.eventName = eventName;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.groupColor = groupColor;
    this.location = location;
    this.repeat = repeat;
    this.repeatOffset = repeatOffset;
    this.sendReminders = sendReminders;
    this.note = note;
    this.graded = graded;
    this.grade = grade;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getGroupColor() {
    return groupColor;
  }

  public void setGroupColor(int groupColor) {
    this.groupColor = groupColor;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public long getBaseId() {
    return baseId;
  }

  public void setBaseId(long baseId) {
    this.baseId = baseId;
  }

  public long getRemindOffset() {
    return remindOffset;
  }

  public void setRemindOffset(long remindOffset) {
    this.remindOffset = remindOffset;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Calendar getEventStart() {
    return eventStart;
  }

  public void setEventStart(Calendar eventStart) {
    this.eventStart = eventStart;
  }

  public Calendar getEventEnd() {
    return eventEnd;
  }

  public void setEventEnd(Calendar eventEnd) {
    this.eventEnd = eventEnd;
  }

  public Boolean getSendReminders() {
    return sendReminders;
  }

  public void setSendReminders(Boolean sendReminders) {
    this.sendReminders = sendReminders;
  }

  public Calendar getRemindTime() {
    return remindTime;
  }

  public void setRemindTime(Calendar remindTime) {
    this.remindTime = remindTime;
  }

  public Boolean getRepeat() {
    return repeat;
  }

  public void setRepeat(Boolean repeat) {
    this.repeat = repeat;
  }

  public int getRepeatOffset() {
    return repeatOffset;
  }

  public void setRepeatOffset(int repeatOffset) {
    this.repeatOffset = repeatOffset;
  }

  public boolean isGraded() {
    return graded;
  }

  public void setGraded(boolean graded) {
    this.graded = graded;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public String getReviewNote() {
    return reviewNote;
  }

  public void setReviewNote(String reviewNote) {
    this.reviewNote = reviewNote;
  }

  /**
   * Converts hour and minute variables into a properly formatted string.
   */
  public String getFormattedTime() {
    @SuppressLint("DefaultLocale") String startHour = String.format(
            "%02d", this.eventStart.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String startMin = String.format(
            "%02d", this.eventStart.get(Calendar.MINUTE));
    @SuppressLint("DefaultLocale") String endHour = String.format(
            "%02d", this.eventEnd.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String endMin = String.format(
            "%02d", this.eventEnd.get(Calendar.MINUTE));
    if (startHour.equals("00")) {
      startHour = "12";
    }
    if (endHour.equals("00")) {
      endHour = "12";
    }
    return startHour + ":" + startMin + " - " + endHour + ":" + endMin;
  }

  /**
   * Converts hour and minute variables into a properly formatted string.
   */
  public String getFormattedStartTime() {
    @SuppressLint("DefaultLocale") String startHour = String.format(
            "%02d", this.eventStart.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String startMin = String.format(
            "%02d", this.eventStart.get(Calendar.MINUTE));
    if (startHour.equals("00")) {
      startHour = "12";
    }
    return startHour + ":" + startMin;
  }

  /**
   * Converts hour and minute variables into a properly formatted string.
   */
  public String getFormattedEndTime() {
    @SuppressLint("DefaultLocale") String endHour = String.format(
            "%02d", this.eventEnd.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String endMin = String.format(
            "%02d", this.eventEnd.get(Calendar.MINUTE));
    if (endHour.equals("00")) {
      endHour = "12";
    }
    return endHour + ":" + endMin;
  }

  /**
   * Gets color group data from event to display proper color in the day view.
   */
  public int getGroupColoredBox() {
    switch (this.groupColor) {
      case R.color.orange:
        return R.drawable.orange_box;
      case R.color.yellow:
        return R.drawable.yellow_box;
      case R.color.green:
        return R.drawable.green_box;
      case R.color.blue:
        return R.drawable.blue_box;
      case R.color.purple_200:
        return R.drawable.purple_box;
      default:
        return R.drawable.red_box;
    }
  }

  /**
   * Converts calendar data into a properly formatted date string.
   */
  public static String getFormattedDate(Calendar calendar) {
    return (calendar.get(Calendar.MONTH) + 1) + "/"
            + calendar.get(Calendar.DAY_OF_MONTH) + "/"
            + calendar.get(Calendar.YEAR);
  }

  /**
   * Converts calendar data into a properly formatted date string for the database.
   */
  public static String getDbFormattedDate(Calendar calendar) {
    return String.format(Locale.getDefault(), "%04d-%02d-%02d",
            calendar.get(Calendar.YEAR),
            (calendar.get(Calendar.MONTH) + 1),
            calendar.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Gets color group data from event to display proper color in the event view.
   */
  public int getGroupColoredOutline() {
    switch (this.groupColor) {
      case R.color.orange:
        return R.drawable.orange_boarder;
      case R.color.yellow:
        return R.drawable.yellow_boarder;
      case R.color.green:
        return R.drawable.green_boarder;
      case R.color.blue:
        return R.drawable.blue_boarder;
      case R.color.purple_200:
        return R.drawable.purple_boarder;
      default:
        return R.drawable.red_boarder;
    }
  }
}

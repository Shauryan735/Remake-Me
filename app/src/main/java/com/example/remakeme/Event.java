package com.example.remakeme;

import android.annotation.SuppressLint;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Calendar;
import java.util.Locale;
/*
 * Documentation for Calendar library:
 * https://developer.android.com/reference/java/util/Calendar
 *
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


  public Event() {
    this.eventStart = Calendar.getInstance();
    this.eventEnd = Calendar.getInstance();
    this.remindTime = Calendar.getInstance();
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

  @Ignore
  public Event(String eventName, Calendar eventStart, Calendar eventEnd,
               int groupColor, String location, Boolean repeat, int repeatOffset, Boolean sendReminders, String note) {
    this.eventName = eventName;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.groupColor = groupColor;
    this.location = location;
    this.repeat = repeat;
    this.repeatOffset = repeatOffset;
    this.sendReminders = sendReminders;
    this.note = note;
  }

  public String getGroupName() { return groupName; }
  public void setGroupName(String groupName) { this.groupName = groupName; }
  public int getGroupColor() { return groupColor; }
  public void setGroupColor(int groupColor) { this.groupColor = groupColor; }
  public void setRepeatOffset(long repeatOffset) { this.repeatOffset = (int) repeatOffset; }
  public long getRemindOffset() { return remindOffset; }
  public void setRemindOffset(long remindOffset) { this.remindOffset = remindOffset; }
  public long getBaseId() { return baseId; }
  public void setBaseId(long baseId) { this.baseId = baseId; }
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }
  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }
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

  public String getReviewNote() { return reviewNote; }
  public void setReviewNote(String reviewNote) { this.reviewNote = reviewNote; }


  public String getFormattedTime(){
    @SuppressLint("DefaultLocale") String startHour = String.format("%02d", this.eventStart.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String startMin = String.format("%02d", this.eventStart.get(Calendar.MINUTE));
    @SuppressLint("DefaultLocale") String endHour = String.format("%02d", this.eventEnd.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String endMin = String.format("%02d", this.eventEnd.get(Calendar.MINUTE));
    if (startHour.equals("00")) {
      startHour = "12";
    }
    if (endHour.equals("00")) {
      endHour = "12";
    }
    return startHour + ":" + startMin + " - " + endHour + ":" + endMin;
  }

  public String getFormattedStartTime(){
    @SuppressLint("DefaultLocale") String startHour = String.format("%02d", this.eventStart.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String startMin = String.format("%02d", this.eventStart.get(Calendar.MINUTE));
    if (startHour.equals("00")) {
      startHour = "12";
    }
    return startHour + ":" + startMin;
  }
  public String getFormattedEndTime(){
    @SuppressLint("DefaultLocale") String endHour = String.format("%02d", this.eventEnd.get(Calendar.HOUR));
    @SuppressLint("DefaultLocale") String endMin = String.format("%02d", this.eventEnd.get(Calendar.MINUTE));
    if (endHour.equals("00")) {
      endHour = "12";
    }
    return endHour + ":" + endMin;
  }

  public int getGroupColoredBox() {
    switch (this.groupColor) {
      case 0xFFFFA500:
        return R.drawable.orange_box;
      case 0xFFFFFF00:
        return R.drawable.yellow_box;
      case 0xFF008000:
        return R.drawable.green_box;
      case 0xFF0000FF:
        return R.drawable.blue_box;
      case 0xFF800080:
        return R.drawable.purple_box;
      default:
        return R.drawable.red_box;
    }
  }

  static public String getFormattedDate(Calendar calendar){
    return (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
  }
  static public String getDBFormattedDate(Calendar calendar){
    return String.format(Locale.getDefault(), "%04d-%02d-%02d",
            calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DAY_OF_MONTH));
  }


  public int getGroupColoredOutline() {
    switch (this.groupColor) {
      case 0xFFFFA500:
        return R.drawable.orange_boarder;
      case 0xFFFFFF00:
        return R.drawable.yellow_boarder;
      case 0xFF008000:
        return R.drawable.green_boarder;
      case 0xFF0000FF:
        return R.drawable.blue_boarder;
      case 0xFF800080:
        return R.drawable.purple_boarder;
      default:
        return R.drawable.red_boarder;
    }
  }
}

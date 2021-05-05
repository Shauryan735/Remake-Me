package com.example.remakeme;

import android.annotation.SuppressLint;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
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
    private int repeatOffset;

    private String note;
    private int groupColor;
    private String location;
    private long baseId;
    private boolean graded;
    private long remindOffset;
    private String groupName;

    public Event() {
    }

    @Ignore
    public Event(long baseId, String eventName, Calendar eventStart, Calendar eventEnd, Boolean sendReminders, Calendar remindTime, Boolean repeat, int repeatOffset) {
        this.baseId = baseId;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.sendReminders = sendReminders;
        this.remindTime = remindTime;
        this.repeat = repeat;
        this.repeatOffset = repeatOffset;
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
                 int groupColor, String location, Boolean repeat, Boolean sendReminders, String note) {
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.groupColor = groupColor;
        this.location = location;
        this.repeat = repeat;
        this.sendReminders = sendReminders;
        this.note = note;
    }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public int getGroupColor() { return groupColor; }
    public void setGroupColor(int groupColor) { this.groupColor = groupColor; }
    public Boolean getGraded() { return graded; }
    public void setGraded(Boolean graded) { this.graded = graded; }
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

    public String getFormattedTime(){
        @SuppressLint("DefaultLocale") String startHour = String.format("%02d", this.eventStart.get(Calendar.HOUR));
        @SuppressLint("DefaultLocale") String startMin = String.format("%02d", this.eventStart.get(Calendar.MINUTE));
        @SuppressLint("DefaultLocale") String endHour = String.format("%02d", this.eventEnd.get(Calendar.HOUR));
        @SuppressLint("DefaultLocale") String endMin = String.format("%02d", this.eventEnd.get(Calendar.MINUTE));
        return startHour + ":" + startMin + " - " + endHour + ":" + endMin;
    }
}

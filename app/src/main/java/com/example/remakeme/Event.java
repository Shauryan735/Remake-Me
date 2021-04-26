package com.example.remakeme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
/*
 * Documentation for Calendar library:
 * https://developer.android.com/reference/java/util/Calendar
 *
 */


@Entity(tableName = "events")
public class Event {

    @PrimaryKey
    @ColumnInfo(name = "event_id")
    private int id;

    private String eventName;
    private Calendar eventStart;
    private Calendar eventEnd;

    private String groupName;
    private int groupColor;

    private Boolean graded;

    private Boolean sendReminders;
    private Calendar remindTime;

    private Boolean repeat;
    private int repeatOffset;

    public Event() {
        this.graded = true;
        this.sendReminders = false;
        this.repeat = false;
        this.eventStart = Calendar.getInstance();
        this.eventEnd = Calendar.getInstance();
        this.remindTime = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(int groupColor) {
        this.groupColor = groupColor;
    }

    public Boolean getGraded() {
        return graded;
    }

    public void setGraded(Boolean graded) {
        this.graded = graded;
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
}

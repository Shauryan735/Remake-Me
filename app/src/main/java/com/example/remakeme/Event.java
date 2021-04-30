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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    private int id;

    private int baseid;

    private String eventName;
    private Calendar eventStart;
    private Calendar eventEnd;

    private String groupName;
    private int groupColor;

    private Boolean graded;

    private Boolean sendReminders;
    private long remindOffset;

    private Boolean repeat;
    private long repeatOffset;

    private String note;
    private String location;

    public Event() {
        this.graded = true;
        this.sendReminders = false;
        this.repeat = false;
        this.eventStart = Calendar.getInstance();
        this.eventEnd = Calendar.getInstance();
    }

    public Event(String eventName, Calendar eventStart, Calendar eventEnd, int groupColor, String location, Boolean repeat, Boolean sendReminders, String note) {
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.groupColor = groupColor;
        this.location = location;
        this.repeat = repeat;
        this.sendReminders = sendReminders;
        this.note = note;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public Calendar getEventStart() { return eventStart; }
    public void setEventStart(Calendar eventStart) { this.eventStart = eventStart; }
    public Calendar getEventEnd() { return eventEnd; }
    public void setEventEnd(Calendar eventEnd) { this.eventEnd = eventEnd; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public int getGroupColor() { return groupColor; }
    public void setGroupColor(int groupColor) { this.groupColor = groupColor; }
    public Boolean getGraded() { return graded; }
    public void setGraded(Boolean graded) { this.graded = graded; }
    public Boolean getSendReminders() { return sendReminders; }
    public void setSendReminders(Boolean sendReminders) { this.sendReminders = sendReminders; }
    public Boolean getRepeat() { return repeat; }
    public void setRepeat(Boolean repeat) { this.repeat = repeat; }
    public long getRepeatOffset() { return repeatOffset; }
    public void setRepeatOffset(long repeatOffset) { this.repeatOffset = repeatOffset; }
    public long getRemindOffset() { return remindOffset; }
    public void setRemindOffset(long remindOffset) { this.remindOffset = remindOffset; }
    public int getBaseid() { return baseid; }
    public void setBaseid(int baseid) { this.baseid = baseid; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

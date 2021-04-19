package com.example.remakeme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity(tableName = "events")
public class Events {

    @PrimaryKey
    @ColumnInfo(name = "event_id")
    private int id;

    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventStart;
    private LocalTime eventEnd;

    private Boolean sendReminders;
    private LocalTime remindTime;

    private Boolean repeat;
    private int repeatOffset;

    public Events() {
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Boolean getSendReminders() {
        return sendReminders;
    }

    public void setSendReminders(Boolean sendReminders) {
        this.sendReminders = sendReminders;
    }

    public LocalTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalTime remindTime) {
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

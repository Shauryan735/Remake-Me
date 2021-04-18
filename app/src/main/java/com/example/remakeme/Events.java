package com.example.remakeme;

import java.time.LocalDate;
import java.time.LocalTime;

public class Events {

    private int _id;
    private String _eventName;
    private LocalDate _eventDate;
    private LocalTime _eventStart;
    private LocalTime _eventEnd;

    private Boolean _sendReminders;
    private LocalTime _remindTime;

    private Boolean _repeat;
    private int _repeatOffset;

    public Events() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_eventName() {
        return _eventName;
    }

    public void set_eventName(String _eventName) {
        this._eventName = _eventName;
    }

    public LocalDate get_eventDate() {
        return _eventDate;
    }

    public void set_eventDate(LocalDate _eventDate) {
        this._eventDate = _eventDate;
    }

    public LocalTime get_eventStart() {
        return _eventStart;
    }

    public void set_eventStart(LocalTime _eventStart) {
        this._eventStart = _eventStart;
    }

    public LocalTime get_eventEnd() {
        return _eventEnd;
    }

    public void set_eventEnd(LocalTime _eventEnd) {
        this._eventEnd = _eventEnd;
    }

    public Boolean get_sendReminders() {
        return _sendReminders;
    }

    public void set_sendReminders(Boolean _sendReminders) {
        this._sendReminders = _sendReminders;
    }

    public LocalTime get_remindTime() {
        return _remindTime;
    }

    public void set_remindTime(LocalTime _remindTime) {
        this._remindTime = _remindTime;
    }

    public Boolean get_repeat() {
        return _repeat;
    }

    public void set_repeat(Boolean _repeat) {
        this._repeat = _repeat;
    }

    public int get_repeatOffset() {
        return _repeatOffset;
    }

    public void set_repeatOffset(int _repeatOffset) {
        this._repeatOffset = _repeatOffset;
    }
}

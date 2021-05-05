package com.example.remakeme;

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
    private int baseid;
    private boolean graded;
    private long remindOffset;
    private String groupName;

    public Event() {
    }

    @Ignore
    public Event(long id, String eventName, Calendar eventStart, Calendar eventEnd, Boolean sendReminders, Calendar remindTime, Boolean repeat, int repeatOffset) {
        this.id = id;
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

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public int getGroupColor() { return groupColor; }
    public void setGroupColor(int groupColor) { this.groupColor = groupColor; }
    public Boolean getGraded() { return graded; }
    public void setGraded(Boolean graded) { this.graded = graded; }
    public void setRepeatOffset(long repeatOffset) { this.repeatOffset = (int) repeatOffset; }
    public long getRemindOffset() { return remindOffset; }
    public void setRemindOffset(long remindOffset) { this.remindOffset = remindOffset; }
    public int getBaseid() { return baseid; }
    public void setBaseid(int baseid) { this.baseid = baseid; }
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

    public void repeat(Event e)
    {
        long id = e.getId();
        String name = e.getEventName();
        Calendar startTime = e.getEventStart();
        Calendar endTime = e.getEventEnd();
        Boolean remind = e.getSendReminders();
        Calendar remindTime = e.getRemindTime();
        Boolean repeat = e.getRepeat(); 							//repeat offset is being treated as follows:
        int repeatOffset = e.getRepeatOffset();						//Daily:    1         |Weekly:   2
        //BiWeekly: 3         |Monthly:  4
        if(repeat)													//Yearly:   5
        {
            if(repeatOffset == 1)									//If repeat set to Daily create new events one year out
            {														//
                int count = 0;										//count init at 0 for one year of events
                while(count < 365)									//check if count is still less than 365
                {													//
                    Calendar nStartTime = startTime;				//create new calendar object for new start time
                    nStartTime.add(Calendar.DAY_OF_YEAR, count+1);	//update the calendar with new time from repeat loop
                    Calendar nEndTime = endTime;					//create new calendar object for new end time
                    nEndTime.add(Calendar.DAY_OF_YEAR, count+1);	//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(id,name,nStartTime,nEndTime,remind,remindTime,repeat,repeatOffset);	//Create a new event with the new calendars
                    count+=1;										//increment the event counter
                }
            }
            else if(repeatOffset == 2)								//If repeat set to Weekly create new events one year out
            {														//
                int count = 0;										//count init at 0 for one year of events
                while(count < 365)									//check if count is still less than 365
                {													//
                    Calendar nStartTime = startTime;				//create new calendar object for new start time
                    nStartTime.add(Calendar.DAY_OF_YEAR, count+7);	//update the calendar with new time from repeat loop
                    Calendar nEndTime = endTime;					//create new calendar object for new end time
                    nEndTime.add(Calendar.DAY_OF_YEAR, count+7);	//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(id,name,nStartTime,nEndTime,remind,remindTime,repeat,repeatOffset);	//Create a new event with the new calendars
                    count+=7;										//increment the event counter by seven days
                }
            }
            else if(repeatOffset == 3)								//If repeat set to BiWeekly create new events one year out
            {														//
                int count = 0;										//count init at 0 for one year of events
                while(count < 365)									//check if count is still less than 365
                {													//
                    Calendar nStartTime = startTime;				//create new calendar object for new start time
                    nStartTime.add(Calendar.DAY_OF_YEAR, count+14);	//update the calendar with new time from repeat loop
                    Calendar nEndTime = endTime;					//create new calendar object for new end time
                    nEndTime.add(Calendar.DAY_OF_YEAR, count+14);	//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(id,name,nStartTime,nEndTime,remind,remindTime,repeat,repeatOffset);	//Create a new event with the new calendars
                    count+=14;										//increment the event counter by seven days
                }
            }
            else if(repeatOffset == 4)								//If repeat set to Monthly create new events one year out
            {														//
                int count = 0;										//count init at 0 for one year of events
                while(count < 12)									//check if count is still less than 365
                {													//
                    Calendar nStartTime = startTime;				//create new calendar object for new start time
                    nStartTime.add(Calendar.MONTH, count+1);		//update the calendar with new time from repeat loop
                    Calendar nEndTime = endTime;					//create new calendar object for new end time
                    nEndTime.add(Calendar.MONTH, count+1);			//update the calendar with new time from repeat loop
                    //
                    Event ne = new Event(id,name,nStartTime,nEndTime,remind,remindTime,repeat,repeatOffset);	//Create a new event with the new calendars
                    count+=1;										//increment the event counter by seven days
                }
            }
            else if(repeatOffset == 5)								//If repeat set to Yearly create an event one year out
            {														//
                Calendar nStartTime = startTime;					//create new calendar object for new start time
                nStartTime.add(Calendar.YEAR, 1);					//update the calendar with new time
                Calendar nEndTime = endTime;						//create new calendar object for new end time
                nEndTime.add(Calendar.YEAR, 1);						//update the calendar with new time
                //
                Event ne = new Event(id,name,nStartTime,nEndTime,remind,remindTime,repeat,repeatOffset);	//Create a new event with the new calendars
            }
        }
    }
}

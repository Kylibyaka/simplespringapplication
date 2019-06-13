package com.epam.entities;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String msg;
    private Date date;
    private DateFormat df;
    private EventType eventType;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}

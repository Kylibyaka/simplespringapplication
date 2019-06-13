package com.epam.entities;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id = new Random().nextInt(50);
    private String msg;
    private Date date;
    private DateFormat df;
    private EventType eventType;

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
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

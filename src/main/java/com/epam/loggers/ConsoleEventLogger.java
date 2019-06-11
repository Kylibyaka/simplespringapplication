package com.epam.loggers;

import com.epam.entities.Event;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event){
        System.out.println(event);
    }

}

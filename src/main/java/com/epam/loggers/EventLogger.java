package com.epam.loggers;

import com.epam.entities.Event;

public interface EventLogger {
    void logEvent(Event event);
}

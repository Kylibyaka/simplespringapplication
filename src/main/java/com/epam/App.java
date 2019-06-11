package com.epam;

import com.epam.entities.Client;
import com.epam.entities.Event;
import com.epam.entities.EventType;
import com.epam.loggers.CacheFileEventLogger;
import com.epam.loggers.CombinedEventLogger;
import com.epam.loggers.EventLogger;
import com.epam.loggers.FileEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event, String msg, EventType eventType) {
        switch (eventType) {
            case INFO: {
                break;
            }
            case ERROR: {
                break;
            }
            default: {
                break;
            }
        }
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean("app", App.class);
        app.logEvent(applicationContext.getBean("event", Event.class), "Some event for 1", EventType.ERROR);
        app.logEvent(applicationContext.getBean("event", Event.class), "Some event for 2", EventType.INFO);

        FileEventLogger fileEventLogger = applicationContext.getBean("fileEventLogger", FileEventLogger.class);
        CacheFileEventLogger cacheFileEventLogger = applicationContext.getBean("cacheFileEventLogger", CacheFileEventLogger.class);
        CombinedEventLogger combinedEventLogger = applicationContext.getBean("combinedEventLogger", CombinedEventLogger.class);
        //fileEventLogger.logEvent(applicationContext.getBean("event", Event.class));

        cacheFileEventLogger.logEvent(applicationContext.getBean("event", Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean("event", Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean("event", Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean("event", Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean("event", Event.class));
        Event endEvent = applicationContext.getBean("event", Event.class);
        endEvent.setMsg("END OF CONTEXT");
        cacheFileEventLogger.logEvent(endEvent);
        applicationContext.close();
    }
}

package com.epam;

import com.epam.config.SimpleConfiguration;
import com.epam.entities.Client;
import com.epam.entities.Event;
import com.epam.entities.EventType;
import com.epam.loggers.CacheFileEventLogger;
import com.epam.loggers.CombinedEventLogger;
import com.epam.loggers.EventLogger;
import com.epam.loggers.FileEventLogger;
import com.epam.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    public static final String EVENT = "event";

    @Autowired
    private Client client;

    private Map<String, EventLogger> loggerMap;

    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger defaultEventLogger;

    @Autowired
    private SimpleService simpleService;

    public App(Client client, Map loggerMap) {
        this.client = client;
        this.loggerMap = loggerMap;
    }

    public void logEvent(Event event, String msg, EventType eventType) {
        event.setMsg(msg);
        loggerMap.get(eventType.toString()).logEvent(event);
        defaultEventLogger.logEvent(event);
        simpleService.addEventToDatabase(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(SimpleConfiguration.class);
        applicationContext.getBeanFactory().getBeanNamesIterator().forEachRemaining(System.out::println);
        App app = applicationContext.getBean("app", App.class);
        app.logEvent(applicationContext.getBean(EVENT, Event.class), "Some event for 1", EventType.ERROR);
        app.logEvent(applicationContext.getBean(EVENT, Event.class), "Some event for 2", EventType.INFO);

        FileEventLogger fileEventLogger = applicationContext.getBean("fileEventLogger", FileEventLogger.class);
        CacheFileEventLogger cacheFileEventLogger = applicationContext.getBean("cacheFileEventLogger", CacheFileEventLogger.class);
        CombinedEventLogger combinedEventLogger = applicationContext.getBean("combinedEventLogger", CombinedEventLogger.class);
        //fileEventLogger.logEvent(applicationContext.getBean("event", Event.class));

        cacheFileEventLogger.logEvent(applicationContext.getBean(EVENT, Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean(EVENT, Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean(EVENT, Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean(EVENT, Event.class));
        cacheFileEventLogger.logEvent(applicationContext.getBean(EVENT, Event.class));
        Event endEvent = applicationContext.getBean(EVENT, Event.class);
        endEvent.setMsg("END OF CONTEXT");
        cacheFileEventLogger.logEvent(endEvent);
        applicationContext.close();
    }
}

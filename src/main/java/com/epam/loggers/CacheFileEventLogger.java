package com.epam.loggers;

import com.epam.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() {
        cache.forEach(super::logEvent);
    }

    public void destroy(){
        if(!cache.isEmpty()){
            writeEventFromCache();
        }
    }
}
